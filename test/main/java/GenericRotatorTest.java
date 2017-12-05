import com.akivaliaho.threed.DirectionalCylinder;
import com.akivaliaho.threed.GenericRotator;
import javafx.scene.shape.Sphere;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Aki on 23.11.2017.
 */
public class GenericRotatorTest {

    private GenericRotator genericRotatorTest;
    private Sphere from;
    private DirectionalCylinder directionalCylinder;
    private Sphere to;

    @Before
    public void setUp() throws Exception {
        from = new Sphere(10);
        to = new Sphere(10);
        directionalCylinder = new DirectionalCylinder(30, 30, from, to);
        this.genericRotatorTest = new GenericRotator(to, directionalCylinder);
    }

    @Test
    public void rotateCylinderTowardsTo() throws Exception {
        genericRotatorTest.rotateCylinderTowardsTo();
        //TODO assert translate is changed towards the new cylinder
    }

}