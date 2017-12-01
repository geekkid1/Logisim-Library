package ihb.logisim.gray_parts;

import java.util.Arrays;
import java.util.List;

import com.cburch.logisim.tools.AddTool;
import com.cburch.logisim.tools.Library;

public class Components extends Library{
	private List<AddTool> tools;
	public Components() {
		tools = Arrays.asList(new AddTool[] {
			new AddTool(new GrayIncrementor()),
			new AddTool(new ThisToolSucks())
		});
	}
	public String getDisplayName() {
		return "Isaac's Tools";
	}
	public List<AddTool> getTools() {
		return tools;
	}
}
