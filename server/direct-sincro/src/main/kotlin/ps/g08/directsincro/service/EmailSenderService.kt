package ps.g08.directsincro.service

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMailMessage
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service

@Service
class EmailSenderService(
    private val emailSender: JavaMailSender
) {
    fun sendEmail(
        subject: String,
        text: String,
        targetEmail: String
    ) {
        val message = emailSender.createMimeMessage()
        val helper = MimeMessageHelper(message)

        helper.setTo(targetEmail)
        helper.setSubject(subject)
        helper.setText(text,true)

        emailSender.send(message)
    }
}