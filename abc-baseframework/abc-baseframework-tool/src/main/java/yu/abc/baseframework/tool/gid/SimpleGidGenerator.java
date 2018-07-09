package yu.abc.baseframework.tool.gid;

import org.springframework.stereotype.Component;

/**
 * Reference from Snowflake algorithm.
 *
 * Created by zsp on 2018/7/9.
 */
@Component
public class SimpleGidGenerator implements GidGenerator {

    @Override
    public long generate() {
        return 0;
    }

}
