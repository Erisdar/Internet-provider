package com.epam.internet_provider.util;

import com.epam.internet_provider.model.Tariff;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TariffUtil {
  public static Tariff createTariff(JSONObject tariffJson) {
    final Tariff tariff = new Tariff();
    tariff.setTitle(tariffJson.getString("title"));
    tariff.setDownloadSpeed(tariffJson.getInt("downloadSpeed"));
    tariff.setUploadSpeed(tariffJson.getInt("uploadSpeed"));
    tariff.setCost(tariffJson.getInt("cost"));
    tariff.setTraffic(tariffJson.getInt("traffic"));
    return tariff;
  }
}
