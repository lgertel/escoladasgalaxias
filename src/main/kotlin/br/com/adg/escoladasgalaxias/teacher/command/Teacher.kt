package br.com.adg.escoladasgalaxias.teacher.command

import br.com.adg.escoladasgalaxias.api.AuditEntry
import br.com.adg.escoladasgalaxias.teacher.command.api.RegisterTeacherCommand
import br.com.adg.escoladasgalaxias.teacher.command.api.TeacherId
import br.com.adg.escoladasgalaxias.teacher.command.api.TeacherRegisteredEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.messaging.annotation.MetaDataValue
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
class Teacher {

	@AggregateIdentifier
	private lateinit var id: TeacherId
	private lateinit var name: String
	private lateinit var email: String

	private constructor()

	@CommandHandler
	constructor(
		command: RegisterTeacherCommand,
		@MetaDataValue(value = "auditEntry") auditEntry: AuditEntry
	) {
		AggregateLifecycle.apply(
			TeacherRegisteredEvent(
				command.name,
				command.email,
				command.targetAggregateIdentifier,
				auditEntry
			)
		)
	}

	fun on(event: TeacherRegisteredEvent) {
		this.id = event.aggregateIdentifier
		this.name = event.name
		this.email = event.email
	}
}