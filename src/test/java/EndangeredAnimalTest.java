import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class EndangeredAnimalTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void endangeredAnimal_instantiatesCorrectly_true() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Fox", "Healthy", "Young");
    assertEquals(true, testEndangeredAnimal instanceof EndangeredAnimal);
  }

  @Test
  public void getHealth_returnsHealthAttribute_true() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Fox", "Healthy", "Young");
    assertEquals("Healthy", testEndangeredAnimal.getHealth());
  }

  @Test
  public void save_assignsIdAndSavesObjectToDatabase() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Fox", "Healthy", "Young");
    testEndangeredAnimal.save();
    System.out.println(testEndangeredAnimal.getId());
    EndangeredAnimal savedEndangeredAnimal = EndangeredAnimal.find(testEndangeredAnimal.getId());
    System.out.println(savedEndangeredAnimal.getId());
    assertEquals(testEndangeredAnimal.getId(), savedEndangeredAnimal.getId());
  }

  @Test
  public void all_returnsAllInstancesOfEndangeredAnimal_true() {
    EndangeredAnimal firstEndangeredAnimal = new EndangeredAnimal("Fox", "Healthy", "Young");
    firstEndangeredAnimal.save();
    EndangeredAnimal secondEndangeredAnimal = new EndangeredAnimal("Badger", "Okay", "Adult");
    secondEndangeredAnimal.save();
    assertEquals(true, EndangeredAnimal.allEndangered().get(0).equals(firstEndangeredAnimal));
    assertEquals(true, EndangeredAnimal.allEndangered().get(1).equals(secondEndangeredAnimal));
  }

  @Test
  public void find_returnsAnimalWithSameId_secondAnimal() {
    EndangeredAnimal firstEndangeredAnimal = new EndangeredAnimal("Fox", "Healthy", "Young");
    firstEndangeredAnimal.save();
    EndangeredAnimal secondEndangeredAnimal = new EndangeredAnimal("Badger", "Okay", "Adult");
    secondEndangeredAnimal.save();
    assertEquals(EndangeredAnimal.find(secondEndangeredAnimal.getId()), secondEndangeredAnimal);
  }

  // @Test
  // public void update_updatesHealthAttribute_true() {
  //   EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Fox", "Healthy", "Young");
  //   testEndangeredAnimal.save();
  //   testEndangeredAnimal.updateHealth("ill");
  //   assertEquals("ill", EndangeredAnimal.find(testEndangeredAnimal.getId()).getHealth());
  // }
  //
  // @Test
  // public void update_updatesAgeAttribute_true() {
  //   EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Fox", "Healthy", "Young");
  //   testEndangeredAnimal.save();
  //   testEndangeredAnimal.updateAge("Adult");
  //   assertEquals("Adult", EndangeredAnimal.find(testEndangeredAnimal.getId()).getAge());
  // }
  //
  // public void updateName_updatesAnimalNameInDatabase_String() {
  //   EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Deer", "Healthy", "Young");
  //   testEndangeredAnimal.save();
  //   testEndangeredAnimal.updateName("Buck");
  //   assertEquals("Buck", testEndangeredAnimal.getName());
  // }


}
