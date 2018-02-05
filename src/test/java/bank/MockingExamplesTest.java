package bank;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

//@RunWith(MockitoJUnitRunner.class) // Init mock (Version B)
public class MockingExamplesTest {

    @Rule// Init mock (Version C)
    public MockitoRule mockitoRule = MockitoJUnit.rule();
//    public MockitoRule mockitoRule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS); // will fail when unnecessary stubbing was made.


    @Mock
    private Account accountMock;

//    @Before // Init mock (Version A)
//    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
//    }

    @Test
    public void shouldCreateSpyOfArrayList() throws Exception {
        List<String> concreteArrayList = new ArrayList<>();
        concreteArrayList.add("Ala");
        concreteArrayList.add("ma");
        concreteArrayList.add("Kota");


        List<String> spyOfStringList = Mockito.spy(concreteArrayList);

        assertThat(spyOfStringList.get(2)).isEqualTo("Kota");
        assertThat(spyOfStringList.size()).isEqualTo(3);

        when(spyOfStringList.get(0)).thenReturn("Janusz");

        assertThat(spyOfStringList.get(0)).isEqualTo("Janusz");
    }

    @Test
    public void shouldCreateMock() throws Exception {

        assertThat(accountMock).isNotNull();
    }

    @Test
    public void shouldReturnForAnyIndex() throws Exception {
        List<String> listMock = Mockito.mock(List.class);

        when(listMock.get(any(Integer.class))).thenReturn("Kot");

        assertThat(listMock.get(100)).isEqualTo("Kot");
    }
}
