package coin;

import model.OptionEvent;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CollectionTest {

    @Test
    public void CollectionTest() {
        OptionEvent collectionEvent = new OptionEvent(8);
        List<Double> diffOptionsPresence = collectionEvent.getDiffOptionsPresence(4);
        Assert.assertEquals(1d / 512d, diffOptionsPresence.get(0), 0d);
        Assert.assertEquals(49d / 512d, diffOptionsPresence.get(1), 0d);
        Assert.assertEquals(252d / 512d, diffOptionsPresence.get(2), 0d);
        Assert.assertEquals(210d / 512d, diffOptionsPresence.get(3), 0d);
    }
}
