package syed.shahza.harmonia.endtoend.test.service.page;

import java.util.Optional;

import syed.shahza.harmonia.endtoend.test.component.Button;
import syed.shahza.harmonia.endtoend.test.component.Input;
import syed.shahza.harmonia.endtoend.test.component.Text;

public interface Page {
    void navigateTo(String url);

    Optional<Button> findButtonByClass(String clazz);

    Optional<Input> findInputByName(String name);

	Optional<Text> findTextByString(String name);

	Optional<Button> findButtonByName(String name);
}
