package top.misec;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import top.misec.config.Config;
import top.misec.login.ServerVerify;
import top.misec.login.Verify;
import top.misec.task.DailyTask;


/**
 * @author cmcc
 * @create 2020/10/11 2:29
 */
public class BiliMain {

    static Logger logger = (Logger) LogManager.getLogger(BiliMain.class.getName());

    public static void main(String[] args) {

        if (args.length < 4) {
            logger.info("任务启动失败");
            logger.warn("Cookies参数缺失，请检查是否在Github Secrets中配置Cookies参数");
        }
        //读取环境变量
        Verify.verifyInit(args[0], args[1], args[2],args[3]);

        if (args.length > 4) {
            ServerVerify.verifyInit(args[4]);
        }
        Config.getInstance().configInit();
        //每日任务65经验
        logger.debug("任务启动中");
        DailyTask dailyTask = new DailyTask();
        dailyTask.doDailyTask();
    }

}
