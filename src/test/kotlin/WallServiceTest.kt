import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import ru.netology.Comment
import ru.netology.Likes
import ru.netology.Post
import ru.netology.WallService

class WallServiceTest {

    @Before
    fun clearBeforeTest() {
        WallService.clear()
        WallService.add(Post(text = "being", comment = Comment(), likes = Likes()))
    }

    @Test
    fun add() {
        val post = WallService.add(Post(comment = Comment(), likes = Likes()))
        var test = true
        if (post.id == 0) test = false
        assertTrue(test)
    }

    @Test
    fun updateTrue() {
        var test = true
        val updatePost = Post(text = "end", comment = Comment(), likes = Likes(), id = 1)
        test = WallService.update(updatePost)
        assertTrue(test)
    }

    @Test
    fun updateFalse() {
        var test = true
        val updatePost = Post(text = "end", comment = Comment(), likes = Likes(), id = 99)
        test = WallService.update(updatePost)
        assertFalse(test)
    }
}