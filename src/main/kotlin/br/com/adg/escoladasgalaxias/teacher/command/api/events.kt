package br.com.adg.escoladasgalaxias.teacher.command.api

import br.com.adg.escoladasgalaxias.api.AuditEntry
import java.io.Serializable

abstract class AuditableAbstractEvent(open val auditEntry: AuditEntry) :
	Serializable

abstract class TeacherEvent(
	open val aggregateIdentifier: TeacherId,
	override val auditEntry: AuditEntry
) : AuditableAbstractEvent(auditEntry)

abstract class TeacherLessonEvent(
	open val aggregateIdentifier: TeacherLessonId,
	override val auditEntry: AuditEntry
) : AuditableAbstractEvent(auditEntry)

data class TeacherRegisteredEvent(
	val name: String,
	val email: String,
	override val aggregateIdentifier: TeacherId,
	override val auditEntry: AuditEntry
) : TeacherEvent(aggregateIdentifier, auditEntry)

data class TeacherLessonCreatedEvent(
	open val aggregateIdentifier: TeacherLessonId,
	override val auditEntry: AuditEntry
): AuditableAbstractEvent(auditEntry)

data class TeacherLessonAssignedEvent(
	override val aggregateIdentifier: TeacherLessonId,
	override val auditEntry: AuditEntry
): TeacherLessonEvent(aggregateIdentifier, auditEntry)

data class TeacherLessonPublishedEvent(
	override val aggregateIdentifier: TeacherLessonId,
	override val auditEntry: AuditEntry
): TeacherLessonEvent(aggregateIdentifier, auditEntry)



