package com.epam.internet_provider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.internet_provider.service.DecryptionService;

@RestController
@RequestMapping(path = "/encrypt")
public class EncryptController
{

  private DecryptionService decryptionService;

  @Autowired
  public EncryptController( DecryptionService decryptionService )
  {
    this.decryptionService = decryptionService;
  }

  @GetMapping(produces = "text/plain")
  public String getPublicKey()
  {
    return decryptionService.getPublicKey();
  }
}
