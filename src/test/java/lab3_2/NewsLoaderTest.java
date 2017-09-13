package lab3_2;

import edu.iis.mto.staticmock.reader.WebServiceNewsReader;
import edu.iis.mto.staticmock.*;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * Created by Piotrek on 03.04.2017.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ConfigurationLoader.class, NewsReaderFactory.class})
public class NewsLoaderTest {

    private WebServiceNewsReader webServiceNewsReader;

    @Before
    public void setUp(){
        Configuration configuration = Mockito.mock(Configuration.class);
        Mockito.when(configuration.getReaderType()).thenReturn("WS");

        mockStatic(ConfigurationLoader.class);
        ConfigurationLoader configurationLoader = Mockito.mock(ConfigurationLoader.class);
        Mockito.when(ConfigurationLoader.getInstance()).thenReturn(configurationLoader);
        Mockito.when(configurationLoader.loadConfiguration()).thenReturn(configuration);

        webServiceNewsReader = Mockito.mock(WebServiceNewsReader.class);
        mockStatic(NewsReaderFactory.class);
        NewsReaderFactory newsReaderFactory = Mockito.mock(NewsReaderFactory.class);
        Mockito.when(NewsReaderFactory.getReader(configuration.getReaderType())).thenReturn(webServiceNewsReader);
    }
}