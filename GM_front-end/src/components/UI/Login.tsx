import * as React from 'react';
import { AppProvider } from '@toolpad/core/AppProvider';
import {
  SignInPage,
  type AuthProvider,
  type AuthResponse,
} from '@toolpad/core/SignInPage';
import { useTheme } from '@mui/material/styles';

const providers = [{ id: 'credentials', name: 'Email and password' }];

const Login: (
  provider: AuthProvider,
  formData?: FormData,
) => Promise<AuthResponse> | void = async (provider, formData) => {
  const promise = new Promise<AuthResponse>((resolve) => {
    setTimeout(() => {
      const email = formData?.get('email');
      const password = formData?.get('password');
      alert(
        `Signing in with "${provider.name}" and credentials: ${email}, ${password}`,
      );
      // preview-start
      resolve({
        type: 'CredentialsSignin',
        error: 'Invalid credentials.',
      });
      // preview-end
    }, 300);
  });
  return promise;
};

export default function LoginPage() {
  const theme = useTheme();
  return (
    // preview-start
    <AppProvider theme={theme}>
      <SignInPage
        signIn={Login}
        providers={providers}
        slotProps={{ emailField: { autoFocus: false } }}
      />
    </AppProvider>
    // preview-end
  );
}