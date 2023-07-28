package ru.netology

fun main() {

    testNotes()

}

object CorrectId {
    private var lastId = 0
    //смотрим в то место где у нас хранятся id постов и выдаём свободный

    fun getNewId(id: Int): Int {
        if (id == 0) {
            lastId += 1
            return lastId
        }

        return 0
    }

    fun clearId() {
        lastId = 0
    }
}

data class Post(
    val id: Int = CorrectId.getNewId(0),
    val original: Post?,
    val ownerId: Int = 0,       //Идентификатор владельца стены, на которой размещена запись.
    val fromId: Int = 0,        //Идентификатор автора записи (от чьего имени опубликована запись).
    val date: Int = 0,   //Время публикации записи в формате unixtime.
    val replyOwnerId: Int = 0, //Идентификатор владельца записи, в ответ на которую была оставлена текущая.
    val replyPostId: Int = 0,  //Идентификатор записи, в ответ на которую была оставлена текущая.
    val canDelete: Boolean = false, // Информация о том, может ли текущий пользователь удалить запись (1 — может, 0 — не может).
    val isPinned: Boolean = false, // Информация о том, что запись закреплена.
    val markedAsAds: Boolean = false, // Информация о том, содержит ли запись отметку "реклама" (1 — да, 0 — нет).
    val isFavorite: Boolean = false, // true, если объект добавлен в закладки у текущего пользователя.
    val postponedId: Int = 0, // Идентификатор отложенной записи. Это поле возвращается тогда, когда запись стояла на таймере.
    val postType: String = "post", //String  Тип записи, может принимать следующие значения: post, copy, reply, postpone, suggest.
    val signerId: Int = 0, //Идентификатор автора, если запись была опубликована от имени сообщества и подписана пользователем;
    val copyHistory: CopyHistory?,
    val views: Views = Views(),
    val comments: Comments?,
    val copyright: Copyright = Copyright(),
    val reposts: Reposts?,
    val geo: Geo = Geo(),
    val donut: Donut = Donut(),
    val authorId: Int,
    val authorName: String,
    val text: String,
    val likes: Likes = Likes(),
    val friendsOnly: Boolean = false,
    val canPin: Boolean = false,
    val canEdit: Boolean = true,
    val attachments: Array<Attachments> = emptyArray(),
)

//interface Attachments {
abstract class Attachments

class AttachPhoto(
    val type: String = "photo",
)

class Photo(
    val id: Int, //Идентификатор фотографии.
    val ownerId: Int, //Идентификатор владельца фотографии.
    val photo130: String, //URL изображения для предпросмотра.
    val photo604: String, //URL полноразмерного изображения.
    val photo: AttachPhoto = AttachPhoto(),
) : Attachments() {
    override fun toString(): String {
        return "type: ${photo.type}, id: $id, photo130: $photo130, photo604: $photo604"
    }
}

class AttachAudio(
    val type: String = "audio",
)

class Audio(
    val id: Int, //Идентификатор аудиозаписи.
    val ownerId: Int, //Идентификатор владельца аудиозаписи.
    val artist: String, //Исполнитель
    val title: String, //Название композиции.
    val duration: Int, //Длительность аудиозаписи в секундах.
    val url: String, //Ссылка на mp3.
    val lyricsId: Int, //Идентификатор текста аудиозаписи (если доступно).
    val albumId: Int, //Идентификатор альбома, в котором находится аудиозапись (если присвоен).
    val genreId: Int, //Идентификатор жанра из списка аудио жанров.
    val date: Int, //Дата добавления.
    val noSearch: Boolean, //если включена опция «Не выводить при поиске». Если опция отключена, поле не возвращается.
    val isHq: Boolean, // если аудио в высоком качестве.
    val audio: AttachAudio = AttachAudio(),
) : Attachments() {
    override fun toString(): String {
        return "type: ${audio.type}, id: $id, artist: $artist, title: $title"
    }
}

class AttachVideo(
    val type: String = "video",
)

open class Video(
    val id: Int, //видеозаписи
    val ownerId: Int, //Идентификатор владельца видеозаписи.
    val title: String, //Название видеозаписи.
    val description: String, //Текст описания видеозаписи.
    val duration: String, //Длительность ролика  в секундах.
    val date: Int, //Дата создания видеозаписи в формате Unixtime.
    val comments: Int, //Количество комментариев к видеозаписи.
    val isPrivate: Boolean = true, //Поле возвращается, если видеозапись приватная (например, была загружена в личное сообщение), всегда содержит 1.
    val video: AttachVideo = AttachVideo(),
) : Attachments() {
    override fun toString(): String {
        return "type: ${video.type}, id: $id, title: $title, description: $description"
    }
}

class AttachGraffiti(
    val type: String = "graffiti",
)

class Graffiti(
    val id: Int, //идентификатор граффити
    val ownerId: Int, //Идентификатор автора граффити.
    val photo130: String, //URL изображения для предпросмотра.
    val photo604: String, //URL полноразмерного изображения.
    val graffiti: AttachGraffiti = AttachGraffiti(),
) : Attachments() {
    override fun toString(): String {
        return "type: ${graffiti.type}, id: $id, photo130: $photo130, photo604: $photo604"
    }
}

class AttachVikiPage(
    val type: String = "page",
)

open class VikiPage(
    val id: Int, //идентификатор вики-страницы.
    val vikiPage: AttachVikiPage = AttachVikiPage(),
    val groupId: Int, //Идентификатор группы, которой принадлежит вики-страница.
    val title: String, //Название вики-страницы.
) : Attachments() {
    override fun toString(): String {
        return "type: ${vikiPage.type}, id: $id, title: $title"
    }
}

data class Donut(
    var isDonut: Boolean = false,
    var paidDuration: Int = 100,
    var canPublishFreeCopy: Boolean = true,
    val placeholder: Placeholder = Placeholder(),
    var editMode: String = "duration",
)

data class Placeholder(
    var name: String = "Смотрим на меня",
)

data class Reposts(
    var count: Int = 0,
    var userReposted: Boolean = false,
)

data class Geo(
    val type: String = "", //Фонтан
    val coordinates: String = "", //"56.008772, 92.870401"
    val place: Places = Places(),
)

data class Places(
    val name: String = "",//"Главный фонтан на Театральной площади г. Красноярска"
)

data class Comments(
    var count: Int = 0,
    var canPost: Boolean = true,
    var groupsCanPost: Boolean = true,
    var canClose: Boolean = false,
    var canOpen: Boolean = true,
)

data class Copyright(
    val id: Int = 0, //тупо 0 потому что мы всего-лишьи имитируем ссылку извне
    val link: String = "",
    val name: String = "",
    val type: String = "",
)

data class Views(var count: Int = 0)

data class CopyHistory(
    var posts: Array<Post> = emptyArray(),
)

data class Likes(
    var count: Int = 0,
    var userLikes: Boolean = false,
    var canLike: Boolean = true,
)

data class Comment(
    val id: Int, //Идентификатор комментария.
    val fromId: Int, //Идентификатор автора комментария.
    val date: Int, //Дата создания комментария в формате Unixtime.
    val text: String,
    val parentsStack: Array<Int> = emptyArray(), //Массив идентификаторов родительских комментариев.
    val attachments: Array<Attachments> = emptyArray(),
)

class PostNotFoundException(message: String): RuntimeException(message)

data class NotesAndComment<A, B>(
    var first: A,
    var second: B,
)

fun testNotes() {
    val user = Users(1, "Alex");
    val user1 = Users(12, "Petya");
    val user2 = Users(35, "Kolya");

    val note1 = NotesService.add(user.id, "title1", "text1")
    val note11 = NotesService.add(user.id, "title11", "text111")
    val note111 = NotesService.add(user.id, "title111", "text111")


    val note2 = NotesService.add(user1.id, "title2", "text2")
    val note22 = NotesService.add(user1.id, "title22", "text22")
    val note222 = NotesService.add(user1.id, "title222", "text222")


    val testId1 = NotesService.add(user2.id, "title1", "text1")
    val testId11 = NotesService.add(user2.id,"title11", "text111")
    val testId111 = NotesService.add(user2.id,"title111", "text111")


    val comment7 = NotesService.createComment(note11, user1.id, "Комментарий не изменённый 7")
    val comment81 = NotesService.createComment(note111, user.id, "Комментарий не изменённый 8 первый")
    val comment82 = NotesService.createComment(note111, user.id, "Комментарий не изменённый 8 второй")

    //println(NotesService.notesToString())

    //NotesService.deleteComment(comment81, user.id)
    //println(NotesService.notesToString())

    NotesService.delete(note11)
    //println(NotesService.notesToString())

    NotesService.edit(user.id, note111, "Новый заголовок", "Комментарий 1 второй")
    //println(NotesService.notesToString())

    NotesService.deleteComment(comment82, user.id)
    NotesService.restoreComment(comment82)
    println(NotesService.notesToString())
    //println(NotesService.getComments(note11, user1.id))


}

//Первое вложение User и List
// в Liste Заметки пользователя и Комментарии к заметкам пользователя
// также есть List с удалёнными заметками и комментариями

class Users(
    val id: Int,
    val name: String
)

class Notes(
    val fromId: Int, //Идентификатор автора заметки. Users.id
    val title: String, //Заголовок заметки.
    val text: String, // Текст заметки.
    val nid: String = CorrectId.getNewId(0).toString() // Идентификатор заметки
) {

    override fun toString(): String {
        return " Заметка id $nid заголовок: $title текст: $text пользователь ид: $fromId"
    }
    override fun hashCode(): Int {
        return nid.hashCode()
    }

    fun equals(obj: Notes): Boolean {
        val tmpNotes: Notes = obj as Notes? ?: return false
        return tmpNotes.nid == nid
    }
}

class NotesComment(
    val noteId: String, //Идентификатор заметки.
    val userId: Int,
    val message: String, //Текст комментария.
    val cid: String = CorrectId.getNewId(0).toString() // Уникальный идентификатор комментария
) {
    override fun toString(): String {
        return " Комментарий id $cid ид Заметки: $noteId текст: $message пользователь ид: $userId"
    }
}

object NotesService {
    private var notes: MutableList<NotesAndComment<Int, MutableList<Notes>>> = mutableListOf() // id пользователя и список Заметок
    private var delNotes: MutableList<NotesAndComment<Int, MutableList<Notes>>> = mutableListOf()
    private var comments: MutableList<NotesAndComment<Int, MutableList<NotesComment>>> = mutableListOf() // id заметки и список комментариев
    private var delComments: MutableList<NotesAndComment<Int, MutableList<NotesComment>>> = mutableListOf()

    fun clear() {
        CorrectId.clearId()
        notes = mutableListOf()
        delNotes = mutableListOf()
        comments = mutableListOf()
        delComments = mutableListOf()
    }

    fun add(userId: Int, title: String, text: String): String {
        val note = Notes(userId, title, text)
        val indexList = getIndexUser(userId)

        when(indexList.size) {
            0 -> {
                notes.add(NotesAndComment(userId, mutableListOf(note)))
                return notes.last().second.last().nid //если находим такую же заметку то возвращаем её ид (можно усложнить)
            }

            1 -> {
                notes[indexList[0]].second.add(note)
                return notes[indexList[0]].second.last().nid
            }
        }

        return note.nid

    }

    fun delete(noteId: String): Boolean {

        val indexList = getIndexUser(noteId = noteId)
        val indexComment = getIndexNoteComment(noteId.toInt())
        if (indexComment.size > 0) {
            //при удалении замеки удаляем комментарии без возможности восстановить
            comments[indexComment[0]].second.clear()
        }

        return when (indexList.size) {
            3 -> {
                //если существует индекс юзера в удалённых - добавляем его
                //иначе вставляем в существующий
                val indexListDel = getIndexUser(indexList[2], type = "delNotes")
                if (indexListDel.size == 1) {
                    delNotes[indexListDel[0]].second.add(notes[indexList[0]].second.removeAt(indexList[1]))
                    true
                } else {
                    delNotes.add(NotesAndComment(indexList[2], mutableListOf(notes[indexList[0]].second.removeAt(indexList[1]))))
                    true
                }

            } else -> false
        }

    }

    fun edit(userId: Int, noteId: String, title: String, text: String): Boolean {
        val indexList = getIndexUser(noteId = noteId) //userId = userId,

        return when (indexList.size) {
            3 -> {
                //нашёл все индексы, можем менять
                var tmpNotes: Notes = Notes(userId, title, text, nid = noteId)
                notes[indexList[0]].second.removeAt(indexList[1])
                notes[indexList[0]].second.add(tmpNotes)
                true
            }

            else -> false //либо не нашёл, либо ошибка
        }

    }

    fun get(noteId: String): MutableList<Notes> {

        var tmpNotes: MutableList<Notes> = mutableListOf()
        val indexList = getIndexUser(noteId = noteId)

        when (indexList.size) {
            3 -> {
                tmpNotes.add(notes[indexList[0]].second[indexList[1]])
            }
            else -> {
                return tmpNotes
            }

        }

        return tmpNotes
    }

    //функция динамическая, возвращает список ID вхождения по пользователю
    //или по пользователю и заметке
    //может искать как в notes действующих так и в удалённых
    private fun getIndexUser(userId: Int = -1, noteId: String = "", type: String = "notes"): MutableList<Int> {
        var tmpList: MutableList<Int> = mutableListOf()
        // 0 - index user
        // 1 - index note or no
        // 2 - userId
        val note: MutableList<NotesAndComment<Int, MutableList<Notes>>> = if (type == "notes") notes else delNotes

        for ((index, userNote) in note.withIndex()) {

            if (userId == -1 && noteId != "") {
                for ((indexNote, noteTmp) in note[index].second.withIndex()) {
                    if (noteId == noteTmp.nid) {
                        tmpList.add(index)
                        tmpList.add(indexNote)
                        tmpList.add(note[index].first)
                        return tmpList
                    }
                }
            }

            if (userId == userNote.first && noteId == "") {
                tmpList.add(index)
                return tmpList
            } else if (userId == userNote.first && noteId != "") {
                for ((indexNote, noteTmp) in note[index].second.withIndex()) {
                    if (noteId == noteTmp.nid) tmpList.add(index, indexNote)
                }
                return tmpList
            }

        }

        return tmpList
    }

    private fun getIndexNoteComment(id: Int = -1, cid: String = "", type: String = "comments"): MutableList<Int> {
        var tmpList: MutableList<Int> = mutableListOf()

        // 0 - index Note
        // 1 - index comment or no
        // 2 - id Note

        val comment: MutableList<NotesAndComment<Int, MutableList<NotesComment>>> = if (type == "comments") comments else delComments

        for ((index, idNoteComment) in comment.withIndex()) {

            if (id == -1 && cid != "") {
                for ((indexComment, commentTmp) in comment[index].second.withIndex()) {
                    if (cid == commentTmp.cid) {
                        tmpList.add(index)
                        tmpList.add(indexComment)
                        tmpList.add(comment[index].first)
                        return tmpList
                    }
                }
            }

            if (id == idNoteComment.first && cid == "") {
                tmpList.add(index)
                return tmpList
            } else if (id == idNoteComment.first && cid != "") {
                tmpList.add(index)
                for ((indexNote, noteTmp) in comment[index].second.withIndex()) {
                    if (cid == noteTmp.cid) tmpList.add(indexNote)
                }
                return tmpList
            }

        }

        return tmpList
    }

    fun createComment(noteId: String, userId: Int, message: String): String {
        val comment = NotesComment(noteId, userId, message)
        val indexList = getIndexNoteComment(noteId.toInt())

        when(indexList.size) {
            0 -> {
                comments.add(NotesAndComment(noteId.toInt(), mutableListOf(comment)))
                return comments.last().second.last().cid //если находим такую же заметку то возвращаем её ид (можно усложнить)
            }

            1 -> {
                comments[indexList[0]].second.add(comment)
                return comments[indexList[0]].second.last().cid
            }
        }

        return comment.cid
    }

    fun deleteComment(cid: String, userId: Int): Boolean {

        val indexList = getIndexNoteComment(cid = cid)

        return when (indexList.size) {
            3 -> {
                val indexListDel = getIndexNoteComment(indexList[2], cid = cid, type = "delComments")
                //если существует индекс заметки - добавляем к нему, или же создаём новый
                if (indexListDel.size == 0) {
                    delComments.add(NotesAndComment(indexList[2], mutableListOf(comments[indexList[0]].second.removeAt(indexList[1]))))
                    return true
                } else {
                    delComments[indexListDel[0]].second.add(comments[indexList[0]].second.removeAt(indexList[1]))
                    return true
                }
            } else -> return false
        }

    }

    fun editComment(cid: String, userId: Int, message: String): Boolean {
        val indexList = getIndexNoteComment(cid = cid)

        return when (indexList.size) {
            3 -> {
                //нашёл все индексы, можем менять
                if (comments[indexList[0]].second[indexList[1]].userId == userId) {
                    var tmpNotes: NotesComment = NotesComment(indexList[2].toString(), userId, message, cid)
                    comments[indexList[0]].second.removeAt(indexList[1])
                    comments[indexList[0]].second.add(tmpNotes)
                    true
                } else {
                    false
                }

            }

            else -> false //либо не нашёл, либо ошибка
        }
    }

    fun restoreComment(cid: String): Boolean {
        val indexListDel = getIndexNoteComment(cid = cid, type = "delComments")

        when (indexListDel.size) {
            3 -> {
                //сначала ищем позицию самой заметки, чтобы проверить, не удалена ли она
                val indexNote = getIndexUser(noteId = indexListDel[2].toString())

                if (indexNote.size > 1) {
                    //смотрим индекс Заметки в комментариях
                    val indexListComment = getIndexNoteComment(id = indexListDel[2])
                    //если существует индекс комментария - добавляем к нему
                    if (indexListComment.size == 0) {
                        comments.add(NotesAndComment(indexListDel[2], mutableListOf(delComments[indexListDel[0]].second.removeAt(indexListDel[1]))))
                        return true
                    } else {
                        comments[indexListComment[0]].second.add(delComments[indexListDel[0]].second.removeAt(indexListDel[1]))
                        return true
                    }
                } else {
                    //значит заметка удалена и не надо восстанавливать Комментарий
                    return false
                }

            } else -> return false
        }

        return false
    }

    fun getComments(noteId: String, userId: Int): MutableList<NotesComment>  {

        var tmpNotes: MutableList<NotesComment> = mutableListOf()
        val indexList = getIndexNoteComment(id = noteId.toInt())

        return when (indexList.size) {
            1 -> {
                for ((indexCom, commentUser) in comments[indexList[0]].second.withIndex()) {
                    if (userId == commentUser.userId) {
                        tmpNotes.add(comments[indexList[0]].second[indexCom])
                    }
                }
                /*for (commentUser in comments[indexList[0]].second) {
                    if (userId == commentUser.userId) {
                        tmpNotes.add(commentUser)
                    }
                }*/
                tmpNotes
            }
            else -> {
                tmpNotes
            }

        }

    }

    fun notesToString() {
        println("------------------------------")
        println("Заметки")
        for (note in notes) {
            for (noteIn in note.second) {
                println(noteIn.toString())
            }
        }

        println("------------------------------")
        println("Удалённые заметки")
        for (delNote in delNotes) {
            for (delNoteIn in delNote.second) {
                println(delNoteIn.toString())
            }
        }

        println("------------------------------")
        println("Комментарии")
        for (tmp in comments) {
            for (tmpIn in tmp.second) {
                println(tmpIn.toString())
            }
        }

        println("------------------------------")
        println("Удалённые комментарии")
        for (tmp in delComments) {
            for (tmpIn in tmp.second) {
                println(tmpIn.toString())
            }
        }
    }
}

object WallService {
    private var posts = emptyArray<Post>()
    private var comments = emptyArray<Comment>()

    fun createComment(postId: Int, comment: Comment): Comment {
        for (i in posts) {
            if (i.id == postId) {
                comments += comment
                return comments.last()
            }
        }
        throw PostNotFoundException("no post with id: $postId")

    }

    fun getPostString(postId: Int = 0) {
        when (postId) {
            0 -> for (i in posts) println(i.toString())
            else -> for (i in posts) if (i.id == postId) println(i.toString())
        }

    }

    fun clear() {
        CorrectId.clearId()
        posts = emptyArray()
    }

    fun add(post: Post): Post {
        posts += post
        return posts.last()
    }

    fun getPostAttach(postId: Int) {
        when (postId) {
            0 -> {
                println("У вас отсутствует корректный id поста.")
            }
            else -> {
                for (i in posts) {
                    if (i.id == postId) {
                        for (attach in i.attachments)
                            when (attach) {
                                is Audio -> println("audio:  $attach")
                                is Photo -> println("photo:  $attach")
                                is VikiPage -> println("viki page:  $attach")
                                is Video -> println("video:  $attach")
                                is Graffiti -> println("graffiti:  $attach")
                            }
                    }
                }
            }
        }

    }

    fun update(post: Post): Boolean {
        for ((index, i) in posts.withIndex()) {
            if (i.id == post.id) {
                posts[index] = post.copy(authorName = post.authorName, text = post.text, likes = i.likes, friendsOnly = post.friendsOnly,
                    canPin = post.canPin, canEdit = post.canEdit)
                return true
            }
        }
        return false
    }

    fun likeById(postId: Int) {
        for ((index, post) in posts.withIndex()) {
            if (post.id == postId) {
                posts[index] = post.copy(likes = Likes(post.likes.count + 1, post.likes.userLikes, post.likes.canLike))
            }
        }
    }

}