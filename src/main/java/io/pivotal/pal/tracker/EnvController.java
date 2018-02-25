package io.pivotal.pal.tracker;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnvController {


  private String port;
  private String memoryLimit;
  private String cfInstanceIndex;
  private String cfInstanceAddr;

  public EnvController(
      @Value("${PORT:NO SET}") String port,
      @Value("${MEMORY_LIMIT:NO SET}") String memoryLimit,
      @Value("${CF_INSTANCE_INDEX:NOT SET}") String cfInstanceIndex,
      @Value("${CF_INSTANCE_ADDR:NOT SET}") String cfInstanceAddr) {

    this.port = port;
    this.memoryLimit = memoryLimit;
    this.cfInstanceIndex = cfInstanceIndex;
    this.cfInstanceAddr = cfInstanceAddr;
  }

  @GetMapping("/env")
  public Map<String, String> getEnv() {

    Map<String, String> environmentVariables = new HashMap<>();
    environmentVariables.put("PORT", port);
    environmentVariables.put("MEMORY_LIMIT", memoryLimit);
    environmentVariables.put("CF_INSTANCE_INDEX", cfInstanceIndex);
    environmentVariables.put("CF_INSTANCE_ADDR", cfInstanceAddr);

    return environmentVariables;
  }
}
