package fr.kap35.databases.connections;

import fr.kap35.databases.request.IRequest;
import fr.kap35.databases.request.IResult;
import fr.kap35.databases.request.implemented.SqlRequest;

import java.util.List;
import java.util.concurrent.Semaphore;

public class DatabaseRunner extends Thread implements IRunner {

    IDatabaseConnection connection;
    List<IRequest> requests;
    boolean started = false;

    private final Semaphore semaphore = new Semaphore(1);

    public DatabaseRunner(IDatabaseConnection connection) {
        super();
        this.connection = connection;
    }

    @Override
    public void run() {
        while (started) {
            for (IRequest request : requests) {
                try {
                    semaphore.acquire();
                    ConnectionTools con = connection.getConnection(request.getConnectionName());
                    IResult res = con.executeQuery(request.getQuery());
                    if (request.getCallback() != null)
                        request.getCallback().callback(res);
                    requests.remove(request);
                } catch (InterruptedException e) {
                    return;
                } finally {
                    semaphore.release();
                }
            }
        }
    }

    @Override
    public void addToQueue(IRequest request) throws InterruptedException {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            sleep(10);
            addToQueue(request);
        } finally {
            semaphore.release();
        }
    }

    @Override
    public void runnerStart() {
        started = true;
        start();
    }

    @Override
    public void runnerStop() {
        started = false;
        interrupt();
    }
}
