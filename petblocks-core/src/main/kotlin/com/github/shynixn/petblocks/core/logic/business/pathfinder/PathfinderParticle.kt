package com.github.shynixn.petblocks.core.logic.business.pathfinder

import com.github.shynixn.petblocks.api.PetBlocksApi
import com.github.shynixn.petblocks.api.business.proxy.PetProxy
import com.github.shynixn.petblocks.api.business.service.LoggingService
import com.github.shynixn.petblocks.api.business.service.ParticleService
import com.github.shynixn.petblocks.api.business.service.ProxyService
import com.github.shynixn.petblocks.api.persistence.entity.AIParticle
import com.github.shynixn.petblocks.core.logic.business.extension.relativeBack

class PathfinderParticle(private val aiParticle: AIParticle, private val pet: PetProxy) : BasePathfinder(aiParticle) {
    private val loggingService = PetBlocksApi.resolve(LoggingService::class.java)
    private val proxyService = PetBlocksApi.resolve(ProxyService::class.java)
    private val particleService = PetBlocksApi.resolve(ParticleService::class.java)
    private var lastPlay = System.currentTimeMillis()

    init {
        loggingService.info("[PathfinderParticle] Initialize for pet of player '${proxyService.getPlayerName(pet.getPlayer<Any>())}'.")
    }

    /**
     * Should the goal be executed.
     */
    override fun shouldGoalBeExecuted(): Boolean {
        println("-------")
        loggingService.info("[PathfinderParticle] Execute for next goal ${!pet.isDead}.")
        return !pet.isDead
    }

    /**
     * On execute.
     */
    override fun onExecute() {
        try {
            loggingService.info("[PathfinderParticle] Execute 1")

            if (!pet.meta.particleEnabled) {
                loggingService.info("[PathfinderParticle] Execute 2 - End")
                return
            }

            loggingService.info("[PathfinderParticle] Execute 3")

            val currentMilliSeconds = System.currentTimeMillis()

            loggingService.info("[PathfinderParticle] Execute 4 ${currentMilliSeconds - lastPlay} < ${aiParticle.delayBetweenPlaying * 1000}")

            if (currentMilliSeconds - lastPlay < aiParticle.delayBetweenPlaying * 1000) {
                return
            }

            loggingService.info("[PathfinderParticle] Execute 5")

            lastPlay = currentMilliSeconds
            val petPosition = proxyService.toPosition(pet.getLocation<Any>())
            petPosition.relativePosition(aiParticle.offset.x, aiParticle.offset.y, aiParticle.offset.z)
            loggingService.info("[PathfinderParticle] Execute on relative position ${petPosition.blockX} ${petPosition.blockY} ${petPosition.blockZ} ${petPosition.worldName}")

            particleService.playParticle(
                proxyService.toLocation<Any>(petPosition),
                aiParticle.particle,
                pet.getPlayer<Any>()
            )
        } catch (e: Exception) {
            loggingService.warn("Failed to execute BuffEffectPathfinder.", e)
        }
    }
}
