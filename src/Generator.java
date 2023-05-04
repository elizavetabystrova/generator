import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Generator {
    private static final int MAX_DIGIT_VALUE = 9;
    private static final Map<String, List<Integer>> testTime = new HashMap<>();

    static {
        testTime.put("hour", Arrays.asList(1, 6, 20));
        testTime.put("min", Arrays.asList(1, 5, 30));
        testTime.put("sec", Arrays.asList(1, 10, 45));
    }

    public static void generateTestData(FileWriter fileWriter) throws IOException {
        for (int i = 0; i < 100; i++) {
            fileWriter.write(generateRecord());
        }
    }

    private static String generateRecord() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CallType.getRandomType());
        stringBuilder.append(",");
        stringBuilder.append(generatePhoneNumber());
        stringBuilder.append(",");
        stringBuilder.append(generateDates());
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    private static String generatePhoneNumber() {
        final SecureRandom random = new SecureRandom();
        final StringBuilder number = new StringBuilder();
        number.append(PhoneNumberPrefix.getRandomPrefix());
        for (int i = 0; i < 7; i++) {
            number.append(random.nextInt(MAX_DIGIT_VALUE));
        }
        return number.toString();
    }

    private static String generateDates() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        SplittableRandom splittableRandom = new SplittableRandom();
        int randomHours = splittableRandom.nextInt(23);
        int randomMinutes = splittableRandom.nextInt(59);
        int randomSeconds = splittableRandom.nextInt(59);
        LocalDateTime startDateTime = LocalDateTime.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth(), LocalDateTime.now().getDayOfMonth(),
                randomHours, randomMinutes, randomSeconds);
        LocalDateTime endDateTime;
        System.out.println(startDateTime);
        int randomType = splittableRandom.nextInt(3);
        if (randomType == 0) {
            List<Integer> hour = testTime.get("hour");
            endDateTime = startDateTime.plusHours(hour.get(splittableRandom.nextInt(hour.size())));
        } else if (randomType == 1) {
            List<Integer> min = testTime.get("min");
            endDateTime = startDateTime.plusMinutes(min.get(splittableRandom.nextInt(min.size())));
        } else {
            List<Integer> sec = testTime.get("sec");
            endDateTime = startDateTime.plusSeconds(sec.get(splittableRandom.nextInt(sec.size())));
        }
        String formatToday = startDateTime.format(formatter);
        String formatPlusMinutes = endDateTime.format(formatter);
        return formatToday + "," + formatPlusMinutes;
    }
}
