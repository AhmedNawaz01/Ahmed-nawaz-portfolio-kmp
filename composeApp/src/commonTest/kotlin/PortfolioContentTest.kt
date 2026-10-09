import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PortfolioContentTest {
    @Test
    fun projectCardsHaveUniqueNumbersAndDisplayableCopy() {
        val projects = PortfolioContent.projects

        assertTrue(projects.isNotEmpty(), "At least one project card should be displayed")
        assertEquals(projects.size, projects.map { it.number }.toSet().size, "Project card numbers should be unique")
        projects.forEach { project ->
            assertFalse(project.name.isBlank(), "Project names must not be blank")
            assertFalse(project.domain.isBlank(), "Project domains must not be blank")
            assertFalse(project.summary.isBlank(), "Project summaries must not be blank")
            assertTrue(project.stack.isNotEmpty(), "Project cards should identify relevant technologies")
        }
    }

    @Test
    fun experienceEntriesHaveRequiredLabels() {
        assertTrue(PortfolioContent.experience.isNotEmpty())
        PortfolioContent.experience.forEach { experience ->
            assertFalse(experience.title.isBlank())
            assertFalse(experience.organization.isBlank())
            assertFalse(experience.period.isBlank())
            assertFalse(experience.details.isBlank())
        }
    }

    @Test
    fun expertiseAndEngineeringNotesAreRenderable() {
        assertTrue(PortfolioContent.expertise.isNotEmpty())
        assertTrue(PortfolioContent.expertise.all { it.isNotBlank() })
        assertTrue(PortfolioContent.notes.isNotEmpty())
        assertTrue(PortfolioContent.notes.all { it.isNotBlank() })
    }
}
