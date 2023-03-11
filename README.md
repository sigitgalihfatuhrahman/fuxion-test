# DOCUMENTATION NOTE BEFORE RUN AT LOCAL

1. SPRING 2.5.0 JAVA 11
2. PLEASE CREATE DATABASE : fuxion
3. running at local check at : localhost:8082 
---------------------------------------------
API LIST
---------------------------------------------
{{basUrl}} ==> localhost:8082 --> create at manage environment at POSTMENT

1. CREATE --> {{baseUrl}}/api/user/create
   * REQUSET : 
    {
      "name": "sigit",
      "email": "galih@gmail.com",
      "address": "jl Bantul jaya, jakarta, Indonesia"
    }
   * REPONSE : 
    {
      "message": "Success",
      "data": {
          "name": "sigit",
          "email": "galih@gmail.com",
          "address": "jl Bantul jaya, jakarta, Indonesia",
          "exported": 0,
          "fileName": null, --> **when data not yet exported**
          "documentFile": null --> **when data not yet exported**
      }
    }
    
2. VIEW --> {{baseUrl}}/api/user/view-all
   * RESPONSE = 
     {
        "data": [
            {
                "id": 1,
                "name": "poli",
                "email": "poli@gmail.com",
                "address": "jl swadaya ujung",
                "exported": 1,
                "file_name": null,
                "document_file": null
            }
        ],
        "total_result": 3,
        "total_page": 1 --> **PAGINATION**
    }
3. EXPORT --> {{baseUrl}}/api/user/export
   * REQUEST : 
     body : [1] or [1,3,2,4,6] array data
   * RESPONSE : 
     {
          "message": "Success",
          "data": [
              {
                  "id": 3,
                  "name": "sigit",
                  "email": "galih@gmail.com",
                  "address": "jl Bantul jaya, jakarta, Indonesia",
                  "exported": 1, --> **EXPORTED IS UPDATE 0 TO 1**
                  "file_name": "12-03-2023_sigit_document.pdf",
                  "document_file": "JVBERi0xLjQKJeLjz9MKNCAwIG9iago8PC9MZW5ndGggMTgyL0ZpbHRlci9GbGF0ZURlY29kZT4+c3RyZWFtCnicbY3dCoJAEEbv9ynm0qCfXQuTrlIyqIso2BcY2tHWVi3XhN4+NzIKvBm+Yc755s5iyeYBhDwAqRiHib90YbYVIEKQKfPEFEYyZ15kLdmCygb22CJsqCVT3ah2Rw681xdfPfjofcGxrlJt6J93Txzvg/Df/AELghVYnelmCPWSArXpiAyNvqwzt03PVTHIRkrVZG1H5wZiLJuHgRyfOO7mFeumC7tSVSVZjT8FiWQn9gKqqUrGCmVuZHN0cmVhbQplbmRvYmoKMSAwIG9iago8PC9UeXBlL1BhZ2UvTWVkaWFCb3hbMCAwIDU5NSA4NDJdL1Jlc291cmNlczw8L0ZvbnQ8PC9GMSAyIDAgUi9GMiAzIDAgUj4+Pj4vQ29udGVudHMgNCAwIFIvUGFyZW50IDUgMCBSPj4KZW5kb2JqCjggMCBvYmoKPDwvVGl0bGUoMS4xLiBQcm9maWxlKS9QYXJlbnQgNyAwIFIvRGVzdFsxIDAgUi9GaXRIIDc3OV0+PgplbmRvYmoKNyAwIG9iago8PC9UaXRsZSgxLiBBc3Nlc21lbnQgSmF2YSBEZXZlbG9wZXIpL1BhcmVudCA2IDAgUi9GaXJzdCA4IDAgUi9MYXN0IDggMCBSL0Rlc3RbMSAwIFIvRml0SCA4MDZdL0NvdW50IDE+PgplbmRvYmoKNiAwIG9iago8PC9UeXBlL091dGxpbmVzL0ZpcnN0IDcgMCBSL0xhc3QgNyAwIFIvQ291bnQgMj4+CmVuZG9iagoyIDAgb2JqCjw8L1R5cGUvRm9udC9TdWJ0eXBlL1R5cGUxL0Jhc2VGb250L1RpbWVzLUJvbGQvRW5jb2RpbmcvV2luQW5zaUVuY29kaW5nPj4KZW5kb2JqCjMgMCBvYmoKPDwvVHlwZS9Gb250L1N1YnR5cGUvVHlwZTEvQmFzZUZvbnQvSGVsdmV0aWNhL0VuY29kaW5nL1dpbkFuc2lFbmNvZGluZz4+CmVuZG9iago1IDAgb2JqCjw8L1R5cGUvUGFnZXMvQ291bnQgMS9LaWRzWzEgMCBSXT4+CmVuZG9iago5IDAgb2JqCjw8L1R5cGUvQ2F0YWxvZy9QYWdlcyA1IDAgUi9PdXRsaW5lcyA2IDAgUj4+CmVuZG9iagoxMCAwIG9iago8PC9Qcm9kdWNlcihpVGV4dK4gNS41LjEzLjIgqTIwMDAtMjAyMCBpVGV4dCBHcm91cCBOViBcKEFHUEwtdmVyc2lvblwpKS9DcmVhdGlvbkRhdGUoRDoyMDIzMDMxMjAyNDI1MCswNycwMCcpL01vZERhdGUoRDoyMDIzMDMxMjAyNDI1MCswNycwMCcpL1RpdGxlKERvY3VtZW50IEFwcEZ1eGlvbikvU3ViamVjdChBc3Nlc3RtZW50KS9LZXl3b3JkcyhKYXZhKS9BdXRob3IoQXBwRnV4aW9uKS9DcmVhdG9yKFNpZ2l0IEdhbGloIGYpPj4KZW5kb2JqCnhyZWYKMCAxMQowMDAwMDAwMDAwIDY1NTM1IGYgCjAwMDAwMDAyNjQgMDAwMDAgbiAKMDAwMDAwMDY0NCAwMDAwMCBuIAowMDAwMDAwNzMzIDAwMDAwIG4gCjAwMDAwMDAwMTUgMDAwMDAgbiAKMDAwMDAwMDgyMSAwMDAwMCBuIAowMDAwMDAwNTc5IDAwMDAwIG4gCjAwMDAwMDA0NTkgMDAwMDAgbiAKMDAwMDAwMDM4NSAwMDAwMCBuIAowMDAwMDAwODcyIDAwMDAwIG4gCjAwMDAwMDA5MzIgMDAwMDAgbiAKdHJhaWxlcgo8PC9TaXplIDExL1Jvb3QgOSAwIFIvSW5mbyAxMCAwIFIvSUQgWzxmYjNkNTczYzc1ZDA1MWU4ODYwZTkwZjQ5NWM3M2EzYT48ZmIzZDU3M2M3NWQwNTFlODg2MGU5MGY0OTVjNzNhM2E+XT4+CiVpVGV4dC01LjUuMTMuMgpzdGFydHhyZWYKMTE5NQolJUVPRgo="
              }
          ]
      }

4. VIEW LIST EXPORTED --> {{baseUrl}}/api/user/view-exported
   * RESPONSE : 
      {
          "data": [
              {
                  "id": 1,
                  "name": "poli",
                  "email": "poli@gmail.com",
                  "address": "jl swadaya ujung",
                  "exported": 1,
                  "file_name": "12-03-2023_poli_document.pdf",
                  "document_file": "JVBERi0xLjQKJeLjz9MKNCAwIG9iago8PC9MZW5ndGggMTY1L0ZpbHRlci9GbGF0ZURlY29kZT4+c3RyZWFtCnicbc3BDoIwDAbge5+iRzyIGxoknsSIBw9Gk73A4gqBbAyZYHx7nQGjCbf+6fe3N9gJWMaYsBiFAobzaO2HxYEjT1DkEPAQZ6KCIHWOnKH6jkfZS9xTT9o21PolQzbWV996PNTHA+fW5qWmf++feB8hjz7+JA3hBhuryykZZEaWegDbwofwas0kTZVqybk3rjS6h1TyKbGrurr44ZmAC7wA5hNEAQplbmRzdHJlYW0KZW5kb2JqCjEgMCBvYmoKPDwvVHlwZS9QYWdlL01lZGlhQm94WzAgMCA1OTUgODQyXS9SZXNvdXJjZXM8PC9Gb250PDwvRjEgMiAwIFIvRjIgMyAwIFI+Pj4+L0NvbnRlbnRzIDQgMCBSL1BhcmVudCA1IDAgUj4+CmVuZG9iago4IDAgb2JqCjw8L1RpdGxlKDEuMS4gUHJvZmlsZSkvUGFyZW50IDcgMCBSL0Rlc3RbMSAwIFIvRml0SCA3NzldPj4KZW5kb2JqCjcgMCBvYmoKPDwvVGl0bGUoMS4gQXNzZXNtZW50IEphdmEgRGV2ZWxvcGVyKS9QYXJlbnQgNiAwIFIvRmlyc3QgOCAwIFIvTGFzdCA4IDAgUi9EZXN0WzEgMCBSL0ZpdEggODA2XS9Db3VudCAxPj4KZW5kb2JqCjYgMCBvYmoKPDwvVHlwZS9PdXRsaW5lcy9GaXJzdCA3IDAgUi9MYXN0IDcgMCBSL0NvdW50IDI+PgplbmRvYmoKMiAwIG9iago8PC9UeXBlL0ZvbnQvU3VidHlwZS9UeXBlMS9CYXNlRm9udC9UaW1lcy1Cb2xkL0VuY29kaW5nL1dpbkFuc2lFbmNvZGluZz4+CmVuZG9iagozIDAgb2JqCjw8L1R5cGUvRm9udC9TdWJ0eXBlL1R5cGUxL0Jhc2VGb250L0hlbHZldGljYS9FbmNvZGluZy9XaW5BbnNpRW5jb2Rpbmc+PgplbmRvYmoKNSAwIG9iago8PC9UeXBlL1BhZ2VzL0NvdW50IDEvS2lkc1sxIDAgUl0+PgplbmRvYmoKOSAwIG9iago8PC9UeXBlL0NhdGFsb2cvUGFnZXMgNSAwIFIvT3V0bGluZXMgNiAwIFI+PgplbmRvYmoKMTAgMCBvYmoKPDwvUHJvZHVjZXIoaVRleHSuIDUuNS4xMy4yIKkyMDAwLTIwMjAgaVRleHQgR3JvdXAgTlYgXChBR1BMLXZlcnNpb25cKSkvQ3JlYXRpb25EYXRlKEQ6MjAyMzAzMTIwMDAyMjErMDcnMDAnKS9Nb2REYXRlKEQ6MjAyMzAzMTIwMDAyMjErMDcnMDAnKS9UaXRsZShEb2N1bWVudCBBcHBGdXhpb24pL1N1YmplY3QoQXNzZXN0bWVudCkvS2V5d29yZHMoSmF2YSkvQXV0aG9yKEFwcEZ1eGlvbikvQ3JlYXRvcihTaWdpdCBHYWxpaCBmKT4+CmVuZG9iagp4cmVmCjAgMTEKMDAwMDAwMDAwMCA2NTUzNSBmIAowMDAwMDAwMjQ3IDAwMDAwIG4gCjAwMDAwMDA2MjcgMDAwMDAgbiAKMDAwMDAwMDcxNiAwMDAwMCBuIAowMDAwMDAwMDE1IDAwMDAwIG4gCjAwMDAwMDA4MDQgMDAwMDAgbiAKMDAwMDAwMDU2MiAwMDAwMCBuIAowMDAwMDAwNDQyIDAwMDAwIG4gCjAwMDAwMDAzNjggMDAwMDAgbiAKMDAwMDAwMDg1NSAwMDAwMCBuIAowMDAwMDAwOTE1IDAwMDAwIG4gCnRyYWlsZXIKPDwvU2l6ZSAxMS9Sb290IDkgMCBSL0luZm8gMTAgMCBSL0lEIFs8MTM5ZmNkOTg4Y2JhNDNiMDVjYTdkYTZjOWFiNDk5MWU+PDEzOWZjZDk4OGNiYTQzYjA1Y2E3ZGE2YzlhYjQ5OTFlPl0+PgolaVRleHQtNS41LjEzLjIKc3RhcnR4cmVmCjExNzgKJSVFT0YK"
              }
          ],
          "total_result": 1,
          "total_page": 1
      }
5. DOWNLOAD --> localhost:8082/api/user/download/1

* LINK POSTMENT PUBLIC
