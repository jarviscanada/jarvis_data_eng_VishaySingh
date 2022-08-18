package ca.jrvs.apps.trading.controller;

import ca.jrvs.apps.trading.domain.PortfolioView;
import ca.jrvs.apps.trading.domain.Trader;
import ca.jrvs.apps.trading.domain.TraderAccountView;
import ca.jrvs.apps.trading.service.DashboardService;
import ca.jrvs.apps.trading.util.ResponseExceptionUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/dashboard")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class DashboardController {

  private final DashboardService dashboardService;

  @Autowired
  public DashboardController(DashboardService dashboardService) {
    this.dashboardService = dashboardService;
  }

  @ApiOperation(value = "Show trader profile by trader ID",
      notes = "Show trader and account details. TraderId and AccountId should be identical")
  @ApiResponses(value = {@ApiResponse(code = 404, message = "traderId or accountId is not found")})
  @ResponseBody
  @ResponseStatus(HttpStatus.OK)
  @GetMapping(path = "/profile/traderId/{traderId}", produces = {
      MediaType.APPLICATION_JSON_UTF8_VALUE})
  public TraderAccountView getAccount(@PathVariable Integer traderId) {
    try {
      return dashboardService.getTraderAccount(traderId);
    } catch (Exception e) {
      throw ResponseExceptionUtil.getResponseStatusException(e);
    }
  }

  @ApiOperation(value = "Show portfolio by trader ID")
  @ApiResponses(value = {@ApiResponse(code = 404, message = "traderId is not found")})
  @GetMapping(path = "/portfolio/traderId/{traderId}")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public PortfolioView getPortfolioView(@PathVariable Integer traderId) {
    try {
      return dashboardService.getProfileViewByTraderId(traderId);
    } catch (Exception e) {
      throw ResponseExceptionUtil.getResponseStatusException(e);
    }
  }

  @ApiOperation(value = "Show all traders", notes = "")
  @ResponseBody
  @ResponseStatus(HttpStatus.OK)
  @GetMapping(path = "/traders", produces = {
      MediaType.APPLICATION_JSON_UTF8_VALUE})
  public List<Trader> getTraders() {
    try {
      return dashboardService.getTraders();
    } catch (Exception e) {
      throw ResponseExceptionUtil.getResponseStatusException(e);
    }
  }

}