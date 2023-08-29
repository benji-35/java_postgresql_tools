package fr.kap35.databases.connections;

import fr.kap35.databases.request.IRequest;

import java.util.List;

public interface IRunner {

    public void addToQueue(IRequest request) throws InterruptedException;

    public void runnerStart();
    public void runnerStop();

}
