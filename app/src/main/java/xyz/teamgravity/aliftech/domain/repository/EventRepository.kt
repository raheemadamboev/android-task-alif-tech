package xyz.teamgravity.aliftech.domain.repository

import xyz.teamgravity.aliftech.data.remote.dto.EventResponseDto

interface EventRepository {

    suspend fun getEvents(): EventResponseDto
}