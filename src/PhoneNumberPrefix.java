import java.security.SecureRandom;

public enum PhoneNumberPrefix {
    PREFIX_TYPE_1("7921"),
    PREFIX_TYPE_2("7911"),
    PREFIX_TYPE_3("7950");
    private final String stringType;

    PhoneNumberPrefix(String str) {
        stringType = str;
    }

    public String getPrefix(PhoneNumberPrefix type) {
        return type.stringType;
    }

    public static String getRandomPrefix() {
        final SecureRandom random = new SecureRandom();
        int rnd = random.nextInt(3);
        if (rnd == 0) {
            return PREFIX_TYPE_1.stringType;
        } else if (rnd == 1) {
            return PREFIX_TYPE_2.stringType;
        } else {
            return PREFIX_TYPE_3.stringType;
        }
    }

}
