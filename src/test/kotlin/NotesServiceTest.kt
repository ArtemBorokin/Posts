package ru.netology

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class NotesServiceTest {

    @Before
    fun clearBeforeTest() {
        NotesService.clear()
    }

    @Test
    fun add() {
        val user = Users(1, "Alex");
        val note111 = NotesService.add(user.id, "title111", "text111")
        assertEquals("1", note111)
    }

    @Test
    fun delete() {
        val user = Users(1, "Alex");
        val note111 = NotesService.add(user.id, "title111", "text111")
        assertTrue(NotesService.delete(note111))
    }

    @Test
    fun edit() {
        val user = Users(1, "Alex");
        val note111 = NotesService.add(user.id, "title111", "text111")
        assertTrue(NotesService.edit(user.id, note111, "новый заголовок", "новый текст"))
    }

    @Test
    fun goodGet() {
        val user = Users(1, "Alex");
        val user1 = Users(12, "Petya");
        val note111 = NotesService.add(user.id, "title111", "text111")
        val note22 = NotesService.add(user1.id, "title22", "text22")
        val getNote = NotesService.get(note111)
        assertEquals(1, getNote.size)
    }

    @Test
    fun badGet() {
        val user = Users(1, "Alex");
        val user1 = Users(12, "Petya");
        val note111 = NotesService.add(user.id, "title111", "text111")
        val note22 = NotesService.add(user1.id, "title22", "text22")
        val getNote = NotesService.get("-1")
        assertEquals(0, getNote.size)
    }

    @Test
    fun createComment() {
        val user = Users(1, "Alex");
        val note111 = NotesService.add(user.id, "title111", "text111")
        val comment82 = NotesService.createComment(note111, user.id, "Комментарий не изменённый 1 второй")
        assertEquals("2", comment82)

    }

    @Test
    fun deleteComment() {
        val user = Users(1, "Alex");
        val note111 = NotesService.add(user.id, "title111", "text111")
        val comment82 = NotesService.createComment(note111, user.id, "Комментарий не изменённый 1 второй")
        assertTrue(NotesService.deleteComment(comment82, user.id))
    }

    @Test
    fun editComment() {
        val user = Users(1, "Alex");
        val user1 = Users(2, "Зуенф");
        val note111 = NotesService.add(user.id, "title111", "text111")
        val comment82 = NotesService.createComment(note111, user.id, "Комментарий не изменённый 1 второй")
        assertTrue(NotesService.editComment(comment82, user.id, "Изменённый коммент"))
    }

    @Test
    fun restoreComment() {
        val user = Users(1, "Alex");
        val user1 = Users(2, "Petya");
        val note111 = NotesService.add(user.id, "title111", "text111")
        val comment82 = NotesService.createComment(note111, user.id, "Комментарий не изменённый 1 второй")
        NotesService.deleteComment(comment82, user.id)
        assertTrue(NotesService.restoreComment(comment82))
    }

    @Test
    fun getComments() {
        val user = Users(1, "Alex");
        val user1 = Users(2, "Petya");
        val note111 = NotesService.add(user.id, "title111", "text111")
        val comment82 = NotesService.createComment(note111, user.id, "Комментарий не изменённый 1 второй")
        val comment83 = NotesService.createComment(note111, user1.id, "Комментарий второй")
        assertEquals(1, NotesService.getComments(note111, user1.id).size)
    }
}