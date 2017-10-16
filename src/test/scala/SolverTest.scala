import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest._
import Solver._

@RunWith(classOf[JUnitRunner])
class SolverTest extends FunSuite {

  test("test of isPossible(...) method"){
    val pref = List((1,'M),(2,'G),(3,'M))
    assert(isPossible(pref)==true)
    val pref2 = List((1,'M),(1,'G),(3,'M))
    assert(isPossible(pref2)==false)
  }

  test("test of removePreferences(...) method"){
    val userPrefs = List( List((1,'M),(2,'M)), List((1,'G)), List((3,'M),(2,'G)) )
    val singlePrefs = List( (1,'M), (1,'G), (2,'G) )
    assert(removePreferences(userPrefs, singlePrefs) == List(List((3,'M))))

    val userPrefs2 = List( List((1,'M),(2,'M)), List((3,'M),(2,'G)) )
    val singlePrefs2 = List( (1,'G), (2,'G) )
    assert(removePreferences(userPrefs2, singlePrefs2) == List(List((3,'M))))
  }

  test("test of simplifyMatrix(...)"){
    //No single preferences
    val pref1 = List(List((1,'G),(2,'G),(3,'M)))
    assert(simplifyMatrix(pref1) == Some((List(), List(List((1,'G),(2,'G),(3,'M))))) )
    //2 single preferences and the matrix can be simplified
    val pref2 = List(List((1,'M)), List((2,'M)), List((1,'G),(2,'G),(3,'M)))
    assert(simplifyMatrix(pref2) == Some((List((1,'M),(2,'M),(3,'M)), List())) )
    //Impossible to find solution
    val pref3 = List( List((1,'M)), List((1,'G)) )
    assert(simplifyMatrix(pref3) == None)
  }
}