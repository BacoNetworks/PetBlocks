package com.github.shynixn.petblocks.api.business.service

interface ProtocolService {
    /**
     * Registers a player for incoming packets.
     * Does nothing if a player is already registered.
     */
    fun <P> registerPlayer(player: P)

    /**
     * UnRegisters a player for incoming packets.
     * Does nothing if a player is already unregistered.
     */
    fun <P> unRegisterPlayer(player: P)

    /**
     * Sends a packet to the given player.
     */
    fun <T, P> sendPacket(packet: T, player: P)

    /**
     * Registers a listener for the given packet type.
     */
    fun <T> registerListener(clazz: Class<T>, f: (T) -> Unit)

    /**
     * Closes all resources and connections.
     */
    fun close()
}
