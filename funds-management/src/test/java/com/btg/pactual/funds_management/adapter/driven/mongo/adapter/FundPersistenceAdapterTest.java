package com.btg.pactual.funds_management.adapter.driven.mongo.adapter;

import com.btg.pactual.funds_management.adapter.driven.mongo.document.FundDocument;
import com.btg.pactual.funds_management.adapter.driven.mongo.mapper.IFundDocumentMapper;
import com.btg.pactual.funds_management.adapter.driven.mongo.repository.IFundRepository;
import com.btg.pactual.funds_management.domain.model.Fund;
import com.btg.pactual.funds_management.data.FundData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static com.btg.pactual.funds_management.data.FundData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FundPersistenceAdapterTest {

    @Mock
    private IFundRepository fundRepository;

    @Mock
    private IFundDocumentMapper fundDocumentMapper;

    @InjectMocks
    private FundPersistenceAdapter fundPersistenceAdapter;

    @Test
    void shouldReturnAllFunds() {

        List<FundDocument> fundDocuments = FundData.getFundDocuments();

        when(fundRepository.findAll()).thenReturn(fundDocuments);


        when(fundDocumentMapper.mapToModel(fundDocuments.get(0))).thenReturn(FundData.getFundA());
        when(fundDocumentMapper.mapToModel(fundDocuments.get(1))).thenReturn(FundData.getFundB());
        when(fundDocumentMapper.mapToModel(fundDocuments.get(2))).thenReturn(new Fund("3", NAME_FUND_C, new BigDecimal("150"), "Category 1"));


        List<Fund> result = fundPersistenceAdapter.findAll();


        assertEquals(3, result.size());
        assertEquals(NAME_FUND_A, result.get(0).getName());
        assertEquals(NAME_FUND_B, result.get(1).getName());
        assertEquals(NAME_FUND_C, result.get(2).getName());
    }

    @Test
    void shouldReturnFundsByCategory() {

        String category = CATEGORY_1;
        List<FundDocument> fundDocuments = List.of(FundData.getFundDocuments().get(0)); // Solo Fund A

        when(fundRepository.findByCategory(category)).thenReturn(fundDocuments);
        when(fundDocumentMapper.mapToModel(fundDocuments.get(0))).thenReturn(FundData.getFundA());

        List<Fund> result = fundPersistenceAdapter.findByCategory(category);

        assertEquals(1, result.size());
        assertEquals(NAME_FUND_A, result.get(0).getName());
    }
}
