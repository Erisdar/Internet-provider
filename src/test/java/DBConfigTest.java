import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import com.epam.internet_provider.dao.TariffDao;
import com.epam.internet_provider.dao.impl.TariffDaoImpl;
import com.epam.internet_provider.model.Tariff;
import com.epam.internet_provider.service.impl.JwtTokeServiceImpl;
import com.epam.internet_provider.util.HashingUtil;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.MethodRule;

import java.util.List;

public class DBConfigTest extends Assert {

    @Rule
    public MethodRule benchmarkRun = new BenchmarkRule();

    @BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 1)
    @Test
    public void testHashing() throws Exception {
        String pass = "Ария Навсегда";

        String hashedPass = HashingUtil.hashString(pass);
        System.out.println(hashedPass);
        System.out.println(HashingUtil.checkString(pass, hashedPass));
    }

    @Test
    public void testSelect() {
        JwtTokeServiceImpl jwtTokeService = new JwtTokeServiceImpl();

        System.out.println(jwtTokeService.readToken());
    }

    @Test
    public void testTariffDao() {
        TariffDao tariffDao = new TariffDaoImpl();

        List<Tariff> tariffs = tariffDao.getTariffs();

        tariffs.forEach(System.out::println);
    }

}
