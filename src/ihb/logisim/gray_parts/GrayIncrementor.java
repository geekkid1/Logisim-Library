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

class GrayIncrementor extends InstanceFactory {
	GrayIncrementor() {
		super("Gray Incrementer");
		setAttributes(new Attribute[] {StdAttr.WIDTH}, new Object[] {BitWidth.create(4)});
		setPorts(new Port[] {/* 0 */ new Port(-30, 0, Port.INPUT, StdAttr.WIDTH), 
							/* 1 */ new Port(0, 0, Port.OUTPUT, StdAttr.WIDTH),});
	}
	public void propagate(InstanceState state) {
        // First we retrieve the value being fed into the input. Note that in
        // the setPorts invocation above, the component's input was included at
        // index 0 in the parameter array, so we use 0 as the parameter below.
        Value in = state.getPort(0);
        
        // Now compute the output. We've farmed this out to a helper method,
        // since the same logic is needed for the library's other components.
        Value out = nextGray(in);
        
        // Finally we propagate the output into the circuit. The first parameter
        // is 1 because in our list of ports (configured by invocation of
        // setPorts above) the output is at index 1. The second parameter is the
        // value we want to send on that port. And the last parameter is its
        // "delay" - the number of steps it will take for the output to update
        // after its input.
        state.setPort(1, out, out.getWidth() + 1);
    }
	 public void paintInstance(InstancePainter painter) {
	        // As it happens, InstancePainter contains several convenience methods
	        // for drawing, and we'll use those here. Frequently, you'd want to
	        // retrieve its Graphics object (painter.getGraphics) so you can draw
	        // directly onto the canvas.
	        painter.drawRectangle(painter.getBounds(), "G+1");
	        painter.drawPorts();
	    }
	 static Value nextGray(Value prev) {
	        BitWidth bits = prev.getBitWidth();
	        if(!prev.isFullyDefined()) return Value.createError(bits);
	        int x = prev.toIntValue();
	        int ct = (x >> 16) ^ x; // compute parity of x
	        ct = (ct >> 8) ^ ct;
	        ct = (ct >> 4) ^ ct;
	        ct = (ct >> 2) ^ ct;
	        ct = (ct >> 1) ^ ct;
	        if((ct & 1) == 0) { // if parity is even, flip 1's bit
	            x = x ^ 1;
	        } else { // else flip bit just above last 1
	            int y = x ^ (x & (x - 1)); // first compute the last 1
	            y = (y << 1) & bits.getMask();
	            x = (y == 0 ? 0 : x ^ y);
	        }
	        return Value.createKnown(bits, x);
	    }


}
