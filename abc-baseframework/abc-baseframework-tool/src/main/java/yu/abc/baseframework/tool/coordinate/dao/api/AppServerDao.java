package yu.abc.baseframework.tool.coordinate.dao.api;

import yu.abc.baseframework.tool.coordinate.domain.AppServer;

/**
 * Created by zsp on 2018/7/9.
 */
public interface AppServerDao {

    AppServer getAppServerByIpAndPort(String ip, Integer port);

}
