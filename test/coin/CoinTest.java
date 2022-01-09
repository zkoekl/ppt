package coin;

import model.OptionEvent;
import org.junit.Assert;
import org.junit.Test;

public class CoinTest {

    @Test
    public void coinTest() {
        OptionEvent coinEvent = new OptionEvent(2);
        Assert.assertEquals(0.5, coinEvent.eventChance(1), 0d);
        Assert.assertEquals(0.25, coinEvent.eventChance(2), 0d);
        Assert.assertEquals(0.0009765625, coinEvent.eventChance(10), 0d);
        Assert.assertEquals(0.0000152587890625, coinEvent.eventChance(16), 0d);
    }
}