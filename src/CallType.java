import java.security.SecureRandom;

public enum CallType {
    INCOMING_CALL("02"),
    OUTGOING_CALL("01");
    private final String stringType;

    CallType(String str) {
        stringType = str;
    }

    public static String getType(CallType type) {
        return type.stringType;
    }

    public static String getRandomType() {
        final SecureRandom random = new SecureRandom();
        int rnd = random.nextInt(2);
        if (rnd == 0) {
            return OUTGOING_CALL.stringType;
        } else {
            return INCOMING_CALL.stringType;
        }
    }

}
