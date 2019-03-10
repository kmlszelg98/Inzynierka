package google;

/**
 * Created by Kamil on 08.03.2019.
 */
import Imap.MessageImap;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.AppendValuesResponse;
import com.google.api.services.sheets.v4.model.BatchGetValuesResponse;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

public class GoogleSheetsService {
    private static Sheets sheetsService;
    private static String SPREADSHEET_ID = "1PhYeQzI7OVaNXh4RyOK_ta3d_tnAsN7Cy1Uzw-KL6vk";
    private static String GENERATED_ID = "1O6cx7DwZPy0k5U_pW13DWVEwJZhjDhTkEPal7G1mgfs";

    public GoogleSheetsService() throws IOException, GeneralSecurityException {
        sheetsService = SheetsService.getSheetsService();
    }

    public int getCount() throws IOException {
        List<String> ranges = Arrays.asList("E2","E2");
        BatchGetValuesResponse readResult = sheetsService.spreadsheets().values()
                .batchGet(SPREADSHEET_ID)
                .setRanges(ranges)
                .execute();
        return Integer.parseInt(readResult.getValueRanges().get(0).getValues().get(0).get(0).toString());
    }

    public MessageImap[] readAllMessages() throws IOException {
        int size = getCount()+1;
        MessageImap[] messageImaps = new MessageImap[size];

        for (int i=2;i<=size;i++) {
            List<String> ranges = Arrays.asList("A"+i, "B"+i, "C"+i);
            BatchGetValuesResponse readResult = sheetsService.spreadsheets().values()
                    .batchGet(SPREADSHEET_ID)
                    .setRanges(ranges)
                    .execute();

            String from = readResult.getValueRanges().get(0).getValues().get(0).get(0).toString();
            String topic = readResult.getValueRanges().get(1).getValues().get(0).get(0).toString();
            String value = readResult.getValueRanges().get(2).getValues().get(0).get(0).toString();
            MessageImap messageImap = new MessageImap(from,topic,value);
            messageImaps[i-2] = messageImap;

        }
        return messageImaps;
    }

    public void writeToSheet(String message, int type, int count) throws IOException {

        Object msg = message;
        ValueRange email = new ValueRange().setValues(Arrays.asList(Arrays.asList(msg)));

        UpdateValuesResponse result = sheetsService.spreadsheets().values()
                .update(GENERATED_ID, "A"+count, email)
                .setValueInputOption("RAW")
                .execute();

        Object t = type;
        ValueRange msgType = new ValueRange().setValues(Arrays.asList(Arrays.asList(t)));

        UpdateValuesResponse result2 = sheetsService.spreadsheets().values()
                .update(GENERATED_ID, "B"+count, msgType)
                .setValueInputOption("RAW")
                .execute();
    }

    public String readSingleValue(String index) throws IOException {
        List<String> ranges = Arrays.asList(index);
        BatchGetValuesResponse readResult = sheetsService.spreadsheets().values()
                .batchGet(GENERATED_ID)
                .setRanges(ranges)
                .execute();
        if (readResult.getValueRanges().get(0).getValues() == null) return "";
        return readResult.getValueRanges().get(0).getValues().get(0).get(0).toString();
    }

    public int getResponsesCount() throws IOException {
        List<String> ranges = Arrays.asList("D2","D2");
        BatchGetValuesResponse readResult = sheetsService.spreadsheets().values()
                .batchGet(GENERATED_ID)
                .setRanges(ranges)
                .execute();
        return Integer.parseInt(readResult.getValueRanges().get(0).getValues().get(0).get(0).toString());
    }
}

