import loader.DataManager;

public class App
{
    private final DataManager dataManager;
    public App()
    {
        dataManager = new DataManager();
    }
    public void run()
    {
        dataManager.loadFromCsv();
    }
}
