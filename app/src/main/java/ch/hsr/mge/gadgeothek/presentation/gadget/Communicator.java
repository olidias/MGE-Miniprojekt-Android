package ch.hsr.mge.gadgeothek.presentation.gadget;

public interface Communicator<T> {
    void transmit(T transmittedItem);
}
