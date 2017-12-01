package ihb.logisim.gray_parts;

import com.cburch.logisim.data.Attribute;
import com.cburch.logisim.data.BitWidth;
import com.cburch.logisim.data.Bounds;
import com.cburch.logisim.data.Value;
import com.cburch.logisim.instance.InstanceFactory;
import com.cburch.logisim.instance.InstancePainter;
import com.cburch.logisim.instance.InstanceState;
import com.cburch.logisim.instance.Port;
import com.cburch.logisim.instance.StdAttr;

class ThisToolSucks extends InstanceFactory {
	ThisToolSucks() {
		super("A tool that sucks");
		setAttributes(new Attribute[] {StdAttr.WIDTH}, new Object[] {BitWidth.create(3)});
		setPorts(new Port[] {
				/* 0 */ 
		});
	}
}
