package br.com.adg.escoladasgalaxias.teacher.command.api

import br.com.adg.escoladasgalaxias.api.AuditEntry
import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.io.Serializable

abstract class AuditableAbstractCommand(open val auditEntry: AuditEntry) :
	Serializable

abstract class TeacherCommand(
	open val targetAggregateIdentifier: TeacherId,
	override val auditEntry: AuditEntry
) : AuditableAbstractCommand(auditEntry)

abstract class TeacherLessonCommand(
	open val targetAggregateIdentifier: TeacherLessonId,
	override val auditEntry: AuditEntry
) : AuditableAbstractCommand(auditEntry)

data class RegisterTeacherCommand(
	val name: String,
	val email: String,
	@TargetAggregateIdentifier override val targetAggregateIdentifier: TeacherId = TeacherId(),
	override val auditEntry: AuditEntry
) : TeacherCommand(targetAggregateIdentifier, auditEntry)

data class CreateTeacherLessonCommand(
	override val targetAggregateIdentifier: TeacherLessonId,
	override val auditEntry: AuditEntry
): TeacherLessonCommand(targetAggregateIdentifier, auditEntry)

data class AssignTeacherLessonCommand(
	override val targetAggregateIdentifier: TeacherId,
	override val auditEntry: AuditEntry
): TeacherCommand(targetAggregateIdentifier, auditEntry)

data class PublishTeacherLessonCommand(
	override val targetAggregateIdentifier: TeacherLessonId,
	override val auditEntry: AuditEntry
): TeacherLessonCommand(targetAggregateIdentifier, auditEntry)