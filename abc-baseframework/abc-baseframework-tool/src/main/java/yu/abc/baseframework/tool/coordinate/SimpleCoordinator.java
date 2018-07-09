package yu.abc.baseframework.tool.coordinate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import yu.abc.baseframework.common.utils.NetUtils;
import yu.abc.baseframework.tool.coordinate.dao.api.AppServerDao;
import yu.abc.baseframework.tool.coordinate.domain.AppServer;

import java.net.UnknownHostException;
import java.security.SecureRandom;

/**
 * Created by zsp on 2018/7/9.
 */
@Component
public class SimpleCoordinator implements Coordinator {

    @Value("${server.port}")
    private Integer port;
    @Autowired
    private AppServerDao appServerDao;

    @Override
    public long getMachineSequence() {
        long sequence;
        try {
            // 为防止一台机器部署多个不同端口号的应用,需要把IP字段加上端口号(ip:port)
            String ip = NetUtils.getHostAddress();
            AppServer appServer = appServerDao.getAppServerByIpAndPort(ip, port);
            if(appServer != null) {
                sequence = appServer.getSequence();
            } else {
                sequence = ip.hashCode() & 0x03FF;
//                if(logger.isWarnEnabled()) {
//                    logger.warn("未配置机器\"" + ip + "\"的序号");
//                }
            }
        } catch (UnknownHostException e) {
            sequence = new SecureRandom().nextInt(0x0400);
            //logger.error("获取机器ip异常", e);
        } finally {
//            if(logger.isInfoEnabled()) {
//                logger.info("当前机器的序号: " + machineNo);
//            }
        }

        return sequence;
    }

}
