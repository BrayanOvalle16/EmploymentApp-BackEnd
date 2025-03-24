package co.ucentral.creditaplication.controllers;



import co.ucentral.creditaplication.models.Company;
import co.ucentral.creditaplication.models.Empresa;

import co.ucentral.creditaplication.models.dtos.EmpresaDto;
import co.ucentral.creditaplication.models.dtos.SignUpDto;
import co.ucentral.creditaplication.models.enums.CompanyRole;

import co.ucentral.creditaplication.services.AuthService;

import co.ucentral.creditaplication.services.EmpresaService;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("Empresa")
public class EmpresaControlador {
    private final AuthService service;

    final
    EmpresaService empresaService;

    private final ModelMapper modelMapper;

    public EmpresaControlador(AuthService service, EmpresaService empresaService, ModelMapper modelMapper) {
        this.service = service;
        this.empresaService = empresaService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/empresas")
    public ResponseEntity<List<Empresa>> list() {
        List<Empresa> Companys = empresaService.getAll();
        return new ResponseEntity<>(Companys, HttpStatus.OK);
    }

    @GetMapping(value = "/empresa")
    public ResponseEntity<Empresa> empresaPorId(@RequestParam(value = "id") long id) {
        Optional<Empresa> empresaOptional = empresaService.getById(id);
        if(empresaOptional.isPresent()) {
            return new ResponseEntity<>(empresaOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createEmpresa(
            @RequestPart("empresa") EmpresaDto empresaDto,
            @RequestPart("file") MultipartFile cv) {
        try {
            Company company = service.signUp(new SignUpDto(empresaDto.getNombreEmpresa(), empresaDto.getNombreEmpresa(), CompanyRole.COMPANY));
            var empresa = modelMapper.map(empresaDto, Empresa.class);
            empresa.setCompany(company);
            Empresa companyCreated = empresaService.save(empresa);
            return new ResponseEntity<>(companyCreated, HttpStatus.CREATED);
        } catch (InvalidKeyException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping(value = "/delete")
    public ResponseEntity<Empresa> delete(@RequestParam(value = "id") long id) {
        empresaService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
