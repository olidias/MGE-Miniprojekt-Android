package ch.hsr.mge.gadgeothek.presentation;

public interface Communicator<T> {
    void transmit(T transmittedItem);
}
