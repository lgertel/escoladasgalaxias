package br.com.adg.escoladasgalaxias.teacher.command.api

import java.util.UUID

data class TeacherId(val identifier: UUID = UUID.randomUUID()) {
	override fun toString(): String  = identifier.toString()
}