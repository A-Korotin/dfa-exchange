package com.dfa.dfaserver.invest.service.integration.impl.klever;

import com.dfa.dfaserver.invest.domain.Account;
import com.dfa.dfaserver.invest.domain.Asset;
import com.dfa.dfaserver.invest.domain.OISChain;
import com.dfa.dfaserver.invest.domain.transaction.Transaction;
import com.dfa.dfaserver.invest.exception.integration.InvalidBlockchainResponseException;
import com.dfa.dfaserver.invest.service.integration.Balance;
import com.dfa.dfaserver.invest.service.integration.OISProvider;
import com.dfa.dfaserver.invest.service.integration.impl.klever.dto.KleverAssetDto;
import com.dfa.dfaserver.invest.service.integration.impl.klever.dto.KleverAssetsPageDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * <a href="https://api.mainnet.klever.finance/swagger/index.html">Klever blockchain API</a>
 */
@Service("Klever")
@RequiredArgsConstructor
public class KleverOISProvider implements OISProvider {

    @Value("${app.integration.providers.klever.assets-url}")
    private String assetsUrl;

    private final RestTemplate template;

    private final ObjectMapper mapper;

    private final OISChain kleverChain = new OISChain("Klever");


    private KleverAssetsPageDto getPage(JsonNode root) throws JsonProcessingException {
        JsonNode paginationJson = root.get("pagination");
        return mapper.treeToValue(paginationJson, KleverAssetsPageDto.class);
    }

    private List<KleverAssetDto> getAssets(JsonNode root) throws JsonProcessingException {
        JavaType kleverAssetsType = mapper.getTypeFactory()
                .constructCollectionType(List.class, KleverAssetDto.class);


        JsonNode assetsJson = root.path("data").path("assets");
        return mapper.treeToValue(assetsJson, kleverAssetsType);

    }

    private List<Asset> kleverAssetsHttpRequest() {
        List<Asset> assets = new ArrayList<>();

        KleverAssetsPageDto page = new KleverAssetsPageDto();
        page.next = 1;
        try {
            do {

                ResponseEntity<String> response = template
                        .getForEntity(assetsUrl + "?limit=100&page=" + page.next, String.class);

                if (response.getStatusCode() != HttpStatusCode.valueOf(200)) {
                    throw new InvalidBlockchainResponseException("Klever api returned non-ok status code '%s'"
                            .formatted(response.getStatusCode()));
                }

                JsonNode root = mapper.readTree(response.getBody());
                page = getPage(root);
                List<KleverAssetDto> assetsRetrieved = getAssets(root);

                assetsRetrieved.stream().map(kleverAsset -> new Asset(kleverAsset.getTicker(), kleverChain))
                        .forEach(assets::add);

            } while (page.self != page.totalPages);
        } catch (JsonProcessingException exception) {
            throw new InvalidBlockchainResponseException(exception.getMessage());
        }

        return assets;
    }

    @Override
    public CompletableFuture<List<Asset>> getAssets() {
        return CompletableFuture.supplyAsync(this::kleverAssetsHttpRequest);
    }

    @Override
    public CompletableFuture<List<Balance>> getBalance(Account account) {
        return null;
    }

    @Override
    public CompletableFuture<Balance> getBalance(Account account, Asset asset) {
        return null;
    }

    @Override
    public CompletableFuture<Transaction> sendTransfer(Account from, Account to, Asset asset, int amount) {
        return null;
    }
}
