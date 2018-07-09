package yu.abc.baseframework.tool.gid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import yu.abc.baseframework.tool.coordinate.Coordinator;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Reference from Snowflake algorithm.
 *
 * Created by zsp on 2018/7/9.
 */
@Component
public class SimpleGidGenerator implements GidGenerator {

    private final ReentrantLock lock = new ReentrantLock();

    private volatile long machineSequence = -1;
    private volatile long currentMillis = 0;
    private volatile long incr;

    @Value("${gid.timestamp.offset}")
    private long offset;
    @Autowired
    private Coordinator coordinator;

    @Override
    public long generate() {
        if(machineSequence == -1) {
            machineSequence = coordinator.getMachineSequence();
        }
        try{
            //上锁防止单个节点每毫秒的自增数被耗尽而超用
            lock.lock();
            long millis = System.currentTimeMillis();
            if (currentMillis < millis) {
				//更新当前毫秒，并且自增数归零
                currentMillis = millis;
                incr = 0;
            }
            if (incr >= 0x0FFF) {
                incr = 0;
				//如果当前自增数已达最大值，则归零；判断最新毫秒数，是否已流逝到下一毫秒，否则线程自旋等待时间的流逝
                long m = currentMillis;
                long latestMillis = System.currentTimeMillis();
                while (latestMillis <= m) {
                    Thread.yield();
                    latestMillis = System.currentTimeMillis();
                }
                millis = latestMillis;
                currentMillis = millis;
            }
            //41位时间戳 + 10位机器序号 + 12位自增序列
            return ((millis - offset) << 22) | (machineSequence << 12) | incr++;
        } finally {
            lock.unlock();
        }
    }

}
