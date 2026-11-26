// Pakiet taki sam jak testowanej klasy — konwencja: test leży w tym samym pakiecie w src/test.
package pd14.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pd14.domain.Member;
import pd14.dto.MemberDto;
import pd14.dto.RegisterMemberCommand;
import pd14.exception.MemberAlreadyExistsException;
import pd14.exception.MemberNotFoundException;
import pd14.repository.MemberRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceImplTest {

  @Mock
  private MemberRepository memberRepository;

  @InjectMocks
  private MemberServiceImpl memberService;

  // thorwnBy - UZYWAMY DLA WYJATKOW + ISINSTANCEOF - SPRAWDZAMY JAKI WYJATEK + HESMESSAGECONTAINING - SPRAWDZAMY TEKST MESSAGE

  @Test
  void shouldReturnMemberDtoWhenMemberExists() {
    //given
    Member member = Member.create("Anna", "anna@test.pl");
    member.assignId(1L);
    when(memberRepository.findById(1L)).thenReturn(Optional.of(member));

    // when
    MemberDto result = memberService.findById(1L);

    // then
    assertThat(result).isEqualTo(new MemberDto(1L, "Anna", "anna@test.pl", 0));
  }

  @Test
  void shouldThrowWhenMemberDoesNotExist() {
    // given
    // mock oryginalnie zwraca optional.empty, ale napisane zeby bylo jasne.
    // SUPER WYJASNIA O CO CHODZI Z STUBEM
    when(memberRepository.findById(99L)).thenReturn(Optional.empty());

    // when + then
    assertThatThrownBy(() -> memberService.findById(99L))
        .isInstanceOf(MemberNotFoundException.class)
        .hasMessageContaining("99");
  }

  @Test
  void shouldThrowWhenRegisterRequestIsNull() {
    // when + then
    assertThatThrownBy(() -> memberService.register(null))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Request cannot be null");

    // VERIFYNOINTERCATIONS - SPRAWDZAMY CZY JAKAKOLWIEK METODA W MEMBEREPOSITORY ZOSTALA WYWOLANA
    // NO BO SKORO NIE ZOSTALA WYWOLANA, TO WALIDACJA WYPRZEDZILA WYWOLANIE METODY,
    // I TEST PRZESZLO BO POWIEDZIELISMY MOCKOWI CO MA ZROBIC JAK DOSTANIE NULLA.
    verifyNoInteractions(memberRepository);
  }

  @Test
  void shouldNotSaveMemberWhenEmailAlreadyExists() {
    RegisterMemberCommand command = new RegisterMemberCommand("Anna", "anna@test.pl");
    when(memberRepository.existsByEmail("anna@test.pl")).thenReturn(true);

    assertThatThrownBy(() -> memberService.register(command))
        .isInstanceOf(MemberAlreadyExistsException.class)
        .hasMessageContaining("anna@test.pl");

    verify(memberRepository, never()).save(any());
  }
}

//Pytanie 1:
// - @InjectMocks: to dobry wybór i działa tu przez konstruktor. Warto znać jego trade-off: jeśli
//kiedyś dodasz do serwisu drugą zależność i zapomnisz o @Mock, Mockito po cichu wstawi null i dostaniesz
//NPE gdzieś w środku testu.
//    Ręczne new MemberServiceImpl(memberRepository) w @BeforeEach złapie to już przy kompilacji. Obie
//drogi są OK, tylko wiedz, co wybierasz.


//Pytanie 2:
//Te trzy statyczne gwiazdki pokrywają około 95% tego, czego używasz w testach.

//Zamiast wklejać to ręcznie, niech IDE robi to za ciebie (zakładam IntelliJ):
//
//Szablon pliku testu. W Settings → Editor → File and Code Templates → Code
// edytujesz „JUnit5 Test Class” i wklejasz tam powyższe importy oraz
// @ExtendWith(MockitoExtension.class). Od tej pory Ctrl+Shift+T na klasie
// produkcyjnej → Create New Test generuje gotowy szkielet.
//Wykluczenie mylących pakietów. W Settings → Editor → General → Auto Import
// dodajesz do „Exclude from auto-import” np. org.junit.Assert (stary JUnit 4)
// i org.hamcrest, jeśli ich nie używasz. Wtedy IDE przestaje proponować złe importy.
//Gdy import jednak brakuje: stań na czerwonym when → Alt+Enter → Import static
// method. Możesz też wpisać początek nazwy i nacisnąć Ctrl+Space dwa razy.
// IntelliJ pokaże wtedy także metody statyczne z niezaimportowanych klas.
