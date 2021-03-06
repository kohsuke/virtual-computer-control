package net.java.dev.vcc.api;

import java.util.Set;
import java.util.concurrent.Future;

/**
 * Represents a Virtual Computer.
 */
public interface Computer  extends ManagedObject {

    /**
     * Gets the host that this computer is currently attached to.
     *
     * @return the host that this computer is currently attached to.
     */
    Host getHost();

    /**
     * Returns {@code true} if the host of this computer is currently changing.
     *
     * @return {@code true} if the host of this computer is currently changing.
     */
    boolean isHostChanging();

    /**
     * If {@link #isHostChanging()} is {@code true} then this method returns a {@link Future}
     * for the {@link Host} that the computer is migrating to, otherwise this method will return a completed
     * {@link Future} for the current {@link Host}.  When the host is changing, the future will be completed
     * when the host migration has completed, thus allowing a consumer of the API to
     * be notified when a host migration has completed.
     *
     * @return Either a completed {@link Future} for the current host, or a {@link Future} that
     *         will complete when the computer has finished migrating to a new host.
     */
    Future<Host> getFutureHost();

    /**
     * Gets the power state of this computer.
     *
     * @return the power state of this computer.
     */
    PowerState getState();

    /**
     * Returns {@code true} if the state of this computer is currently changing.
     *
     * @return {@code true} if the state of this computer is currently changing.
     */
    boolean isStateChanging();

    /**
     * If {@link #isStateChanging()} is {@code true} then this method returns a {@link Future}
     * for the state that the computer is transitioning into, otherwise this method will return a completed
     * {@link Future} for the current state.  When the state is changing, the future will be completed
     * when the state transition has completed, thus allowing a consumer of the API to
     * be notified when a power state change has completed.
     *
     * @return Either a completed {@link Future} for the current state, or a {@link Future} that
     *         will complete when the state has finished changing.
     */
    Future<PowerState> getFutureState();

    /**
     * Gets the snapshots of this computer that are currently available.
     *
     * @return the snapshots of this computer that are currently available.
     */
    Set<ComputerSnapshot> getSnapshots();

    /**
     * Gets the hosts that this computer can be migrated to.
     *
     * @return the hosts that this computer can be migrated to.
     */
    Set<Host> getAllowedHosts();

    /**
     * Gets the name of this virtual computer.
     *
     * @return the name of this virtual computer.
     */
    String getName();

    /**
     * Gets the description of this virtual computer or {@code null} if descriptions are not supported.
     *
     * @return the description of this virtual computer or {@code null} if descriptions are not supported.
     */
    String getDescription();

}
