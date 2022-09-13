import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

fun toDateTime(s: String) = LocalDateTime.of(LocalDate.parse(s), LocalTime.MIDNIGHT)
    .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)

context(LanguageContext)
fun toHumanDate(s: String) = LocalDate.parse(s)
    .format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
    .withLocale(language.locale()))
