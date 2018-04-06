package syed.shahza.harmonia.endtoend.test.service.page;

import java.util.Optional;

import syed.shahza.harmonia.endtoend.test.component.Button;
import syed.shahza.harmonia.endtoend.test.component.Input;
import syed.shahza.harmonia.endtoend.test.component.RadioButton;
import syed.shahza.harmonia.endtoend.test.component.Select;
import syed.shahza.harmonia.endtoend.test.component.Text;
import syed.shahza.harmonia.endtoend.test.component.Textarea;

public interface Page {
    void navigateTo(String url);

    Optional<Button> findButtonByClass(String clazz);

    Optional<Input> findInputByName(String name);

	Optional<Text> findTextByString(String name);

	Optional<Button> findButtonByName(String name);

	Optional<Select> findSelectByName(String name);

	Optional<RadioButton> findRadioButtonById(String id);
	
	Optional<Textarea> findTextareaByName(String name);
}
