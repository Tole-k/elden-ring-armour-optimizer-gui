package scraper;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import item.Item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WebScraper
{
    public WebScraper()
    {
    }

    public List<Item> getItems(String url)
    {

        WebClient webClient = new WebClient(BrowserVersion.BEST_SUPPORTED);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setPrintContentOnFailingStatusCode(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        List<Item> items = new ArrayList<>();
        try
        {
            HtmlPage page = webClient.getPage(url);
            webClient.getCurrentWindow().getJobManager().removeAllJobs();
            HtmlTable table = page.getFirstByXPath("//table");
            table.getRows().stream().skip(1).forEach(row ->
            {
                Item item = new Item();
                item.name = row.getCells().getFirst().getTextContent();
                int i = 0;
                for (HtmlTableCell cell : row.getCells().subList(1, row.getCells().size() - 4))
                {
                    item.stats[i] = Float.parseFloat(cell.getTextContent());
                    // System.out.println(item.stats[i]);
                    i++;
                }
                item.weight = Float.parseFloat(row.getCells().get(14).getTextContent());
                items.add(item);

            });
            webClient.close();

        } catch (IOException e)
        {
            System.out.println("An error occurred: " + e);
        }
        return items;
    }
}
