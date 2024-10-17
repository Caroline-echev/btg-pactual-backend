package com.btg.pactual.funds_management.domain.api.usecase;

import com.btg.pactual.funds_management.data.FundData;
import com.btg.pactual.funds_management.domain.model.Fund;
import com.btg.pactual.funds_management.domain.spi.IFundPersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.btg.pactual.funds_management.data.FundData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FundUseCaseTest {

    @Mock
    private IFundPersistencePort fundPersistencePort;

    @InjectMocks
    private FundUseCase fundUseCase;

    @Test
    void shouldReturnFundsFilteredByCategory() {

        List<Fund> funds = FundData.getFunds();
        when(fundPersistencePort.findByCategory(CATEGORY_1)).thenReturn(List.of(
                funds.get(0),
                funds.get(2)
        ));


        List<Fund> resultFunds = fundUseCase.getFunds(CATEGORY_1, false, true);


        assertEquals(2, resultFunds.size());
        assertEquals(NAME_FUND_A, resultFunds.get(0).getName());
        verify(fundPersistencePort, times(1)).findByCategory(CATEGORY_1);
    }
    @Test
    void shouldReturnFundsFilteredByCategoryAndOrderedByNameAsc() {

        List<Fund> funds = FundData.getFunds();
        when(fundPersistencePort.findByCategory(CATEGORY_1)).thenReturn(List.of(
                funds.get(0),
                funds.get(2)
        ));


        List<Fund> resultFunds = fundUseCase.getFunds(CATEGORY_1, true, true);


        assertEquals(2, resultFunds.size());
        assertEquals(NAME_FUND_A, resultFunds.get(0).getName());
        assertEquals(NAME_FUND_C, resultFunds.get(1).getName());
        verify(fundPersistencePort, times(1)).findByCategory(CATEGORY_1);
    }

    @Test
    void shouldReturnAllFundsWhenNoCategoryIsProvided() {

        when(fundPersistencePort.findAll()).thenReturn(FundData.getFunds());

        List<Fund> resultFunds = fundUseCase.getFunds(null, false, true);

        assertEquals(3, resultFunds.size());
        verify(fundPersistencePort, times(1)).findAll();
    }

    @Test
    void shouldReturnFundsOrderedByNameAsc() {

        when(fundPersistencePort.findAll()).thenReturn(FundData.getFunds());

        List<Fund> resultFunds = fundUseCase.getFunds(null, true, true);

        assertEquals(NAME_FUND_A, resultFunds.get(0).getName());
        assertEquals(NAME_FUND_B, resultFunds.get(1).getName());
        assertEquals(NAME_FUND_C, resultFunds.get(2).getName());
        verify(fundPersistencePort, times(1)).findAll();
    }

    @Test
    void shouldReturnFundsOrderedByAmountDesc() {

        when(fundPersistencePort.findAll()).thenReturn(FundData.getFunds());

        List<Fund> resultFunds = fundUseCase.getFunds(null, false, false);

        assertEquals(NAME_FUND_B, resultFunds.get(0).getName());
        assertEquals(NAME_FUND_C, resultFunds.get(1).getName());
        assertEquals(NAME_FUND_A, resultFunds.get(2).getName());
        verify(fundPersistencePort, times(1)).findAll();
    }
    @Test
    void shouldReturnFundsOrderedByNameDesc() {
        List<Fund> funds = FundData.getFunds();
        when(fundPersistencePort.findByCategory(CATEGORY_1)).thenReturn(List.of(
                funds.get(0),
                funds.get(1),
                funds.get(2)
        ));

        List<Fund> resultFunds = fundUseCase.getFunds(CATEGORY_1, true, false);

        assertEquals(NAME_FUND_C, resultFunds.get(0).getName());
        assertEquals(NAME_FUND_B, resultFunds.get(1).getName());
        assertEquals(NAME_FUND_A, resultFunds.get(2).getName());
        verify(fundPersistencePort, times(1)).findByCategory(CATEGORY_1);
    }

}
