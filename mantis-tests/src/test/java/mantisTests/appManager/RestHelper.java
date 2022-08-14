package mantisTests.appManager;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import mantisTests.model.BugifyIssue;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.util.Set;

import static com.google.gson.JsonParser.parseString;

public class RestHelper {

    private ApplicationManager app;

    public RestHelper(ApplicationManager app) {
        this.app = app;
    }

    public Set<BugifyIssue> getIssue(int issueId) throws IOException {
        String json = getExecutor().execute(Request.Get(String.format(app.getProperty("rest.getIssueUrl"), issueId))).returnContent().asString();
        JsonElement parsed = parseString(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<BugifyIssue>>(){}.getType());
    }

    public boolean checkIssueOpen(int issueId) throws IOException {
        String status = getIssue(issueId).iterator().next().getState_name();
        return !("Resolved".equals(status) || "Closed".equals(status));
    }

    private Executor getExecutor() {
        return Executor.newInstance().auth(app.getProperty("rest.token"), "");
    }
}
