package xyz.neziw.pass4j

import java.awt.GridLayout
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.util.*
import java.util.concurrent.Executors
import javax.swing.*

class Pass4J : JFrame() {

    private val executor = Executors.newSingleThreadExecutor()
    private val passwordField = JPasswordField()
    private val lengthField = JTextField()
    private val additionalCharsField = JTextField()
    private val includeAdditionalChars = JCheckBox("Include additional characters")

    init {
        title = "Password Generator"
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        setSize(450, 340)
        isResizable = false
        layout = GridLayout(5, 2)
        add(JLabel("Password length:"))
        add(lengthField)
        add(JLabel("Additional characters:"))
        add(additionalCharsField)
        add(includeAdditionalChars)
        add(JButton("Generate").apply {
            addActionListener {
                executor.submit {
                    val length = lengthField.text.toInt()
                    val additionalChars = additionalCharsField.text
                    val include = includeAdditionalChars.isSelected
                    val password = generatePassword(length, additionalChars, include)
                    SwingUtilities.invokeLater { passwordField.text = password }
                }
            }
        })
        add(passwordField)
        add(JButton("Copy").apply {
            addActionListener {
                val password = passwordField.text
                val clipboard = Toolkit.getDefaultToolkit().systemClipboard
                val selection = StringSelection(password)
                clipboard.setContents(selection, selection)
            }
        })
        isVisible = true
    }

    private fun generatePassword(length: Int, additionalChars: String, include: Boolean): String {
        val characters = if (include) "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789$additionalChars" else "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
        val password = StringBuilder()
        val random = Random()
        for (i in 0 until length) {
            password.append(characters[random.nextInt(characters.length)])
        }
        return password.toString()
    }
}

fun main() {
    SwingUtilities.invokeLater { Pass4J() }
}
