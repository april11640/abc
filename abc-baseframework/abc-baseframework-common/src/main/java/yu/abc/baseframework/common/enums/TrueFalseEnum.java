package yu.abc.baseframework.common.enums;

/**
 * Created by zsp on 2018/7/9.
 */
public enum TrueFalseEnum {

    FALSE(0),
    TRUE(1);

    private int code;

    private TrueFalseEnum(int code) {
        this.code = code;
    }

}
