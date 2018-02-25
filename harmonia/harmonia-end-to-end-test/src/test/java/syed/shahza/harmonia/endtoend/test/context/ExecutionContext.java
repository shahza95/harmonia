package syed.shahza.harmonia.endtoend.test.context;

import java.util.HashMap;
import java.util.Map;

import syed.shahza.harmonia.backend.dto.LecturerDto;
import syed.shahza.harmonia.endtoend.test.api.Action;
import syed.shahza.harmonia.endtoend.test.api.Result;

public class ExecutionContext {
    private Map<Action, Result> results;
    private LecturerDto currentUser;

    public ExecutionContext() {
        super();
        this.results = new HashMap<Action, Result>();
    }

    public void addResult(Action action, Result result) {
        this.results.put(action, result);
    }

    public Result getLastResultFor(Action action) {
        return this.results.get(action);
    }
    
    public void setCurrentUser(LecturerDto lecturerDto) {
        this.currentUser = lecturerDto;
    }

    public LecturerDto getCurrentUser() {
        return this.currentUser;
    }
}
